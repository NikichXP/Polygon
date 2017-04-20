import java.io.*;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\TEST\\lorem1.txt");
		File dest = new File("C:\\TEST\\lorem3.txt");
		if (dest.exists()) {
			dest.delete();
		}
		dest.createNewFile();
		InputStream fis = new FileInputStream(file);
		OutputStream fos = new FileOutputStream(dest);
		byte[] data = new byte[4096];
		int read;
		while ((read = fis.read(data)) >= 0) {
			if (read == 4096) {
				fos.write(data);
			} else {
				byte tmp [] = new byte[read]; //Arrays.copyOf(data, read)
				for (int i = 0; i < read; i++) {
					tmp[i] = data[i]; //fos.write(data[i])
				}
				fos.write(tmp);
			}
		}
	}
}
