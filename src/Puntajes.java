import java.io.*;
import java.util.*;

public class Puntajes {
    private static final String ARCHIVO = "puntajes.json";
    private static final int MAX = 10;

    public static void guardar(String nombre, int puntos) {
        List<String[]> lista = leer();
        lista.add(new String[]{nombre, String.valueOf(puntos)});
        lista.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));
        if (lista.size() > MAX) lista = lista.subList(0, MAX);
        escribir(lista);
    }

    public static List<String[]> leer() {
        List<String[]> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) sb.append(linea);
            String contenido = sb.toString().trim();
            if (contenido.isEmpty() || contenido.equals("[]")) return lista;
            contenido = contenido.substring(1, contenido.length() - 1);
            for (String entrada : contenido.split("\\},\\{")) {
                entrada = entrada.replace("{", "").replace("}", "");
                String nom = entrada.replaceAll(".*\"nombre\":\"([^\"]+)\".*", "$1");
                String pts = entrada.replaceAll(".*\"puntos\":(\\d+).*", "$1");
                lista.add(new String[]{nom, pts});
            }
        } catch (Exception ignored) {}
        return lista;
    }

    private static void escribir(List<String[]> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
            pw.print("[");
            for (int i = 0; i < lista.size(); i++) {
                if (i > 0) pw.print(",");
                pw.printf("{\"nombre\":\"%s\",\"puntos\":%s}", lista.get(i)[0], lista.get(i)[1]);
            }
            pw.print("]");
        } catch (Exception ignored) {}
    }
}
