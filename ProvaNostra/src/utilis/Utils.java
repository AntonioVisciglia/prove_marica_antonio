package utilis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * Classe di utilità per la lettura del file .json
 * @author Antonio
 *
 */
public class Utils {
	public static final String CONFIG_PATH = "resources/config.json";

	/**
	 * Metodo che prende come parametri Il Tipo di classe che ha all'interno i dati
	 * che saranno nel file .json {@link Configuration}, e il path dove si trova il file.
	 * @param type
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static Object getJsonFile(@SuppressWarnings("rawtypes") Class type, String path) throws FileNotFoundException
	{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, type);
	}

}
