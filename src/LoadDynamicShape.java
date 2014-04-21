
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class LoadDynamicShape {
	
	public Shape load(String path, String name) {
		path="";
		//System.out.println(path+" "+name);
		name=name.replace(".class", "");
		try {
			File file = new File(path);
			// Convert File to a URL
			URL url;

			url = file.toURI().toURL();

			URL[] urls = { url };

			// Create a new class loader with the directory
			URLClassLoader cl = new URLClassLoader(urls);
			Class cls = cl.loadClass(name);

			try {
				return ((Shape)cls.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				System.out.println("Can't Load The Class!");
			}
			
			return null;
			//System.out.println("Done");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
