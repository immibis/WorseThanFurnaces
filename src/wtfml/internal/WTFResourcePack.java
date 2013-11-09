package wtfml.internal;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import mc.IResourceMetadataSection;
import mc.IResourcePack;
import mc.Minecraft;
import mc.ResourceLocation;
import mc.ResourceMetadataSectionTypeRegistry;
import mc.ResourceNotFoundException;
import mc.ResourcePackDefault;
import mc.ResourcePackZipFile;

public class WTFResourcePack implements IResourcePack {

	private URLClassLoader classLoader = (URLClassLoader)WTFResourcePack.class.getClassLoader();
	private Set<String> namespaces = new HashSet<String>();
	
	{
		try {
			for(URL url : classLoader.getURLs()) {
				if(url.getProtocol().equals("file")) {
					File file = Paths.get(url.toURI()).toFile();
					
					if(file.isFile()) {
						ZipInputStream in = new ZipInputStream(new FileInputStream(file));
						try {
							for(ZipEntry ze = in.getNextEntry(); ze != null; ze = in.getNextEntry()) {
								if(ze.isDirectory() && ze.getName().endsWith("/assets/"))
									namespaces.add(ze.getName().substring(0, ze.getName().length() - 8));
							}
						} finally {
							in.close();
						}
						
					} else {
						for(File subdir : file.listFiles())
							if(subdir.isDirectory() && new File(subdir, "assets").isDirectory())
								namespaces.add(subdir.getName());
					}
					
					
				} else
					throw new RuntimeException("Don't know how to scan classpath URL: "+url.toString());
			}
		} catch(URISyntaxException e) {
			throw new RuntimeException(e);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("WTFML namespaces: "+namespaces);
	}
	
	@Override
	public InputStream func_0_InputStreamResourceLocation(ResourceLocation var1) throws ZipException, FileNotFoundException, ResourceNotFoundException, IOException {
		System.out.println("WTFResourcePack: requested file: "+var1);
		String path = var1.func_1_ResourceLocation_String()+"/assets/"+var1.func_0_ResourceLocation_String();
		InputStream is = classLoader.getResourceAsStream(path);
		if(is == null)
			throw new FileNotFoundException(var1.toString());
		return is;
	}

	@Override
	public boolean func_0_ZResourceLocation(ResourceLocation var1) {
		String path = var1.func_1_ResourceLocation_String()+"/assets/"+var1.func_0_ResourceLocation_String();
		return classLoader.getResource(path) != null;
	}

	@Override
	public Set func_0_Set() {
		return namespaces;
	}

	@Override
	public IResourceMetadataSection func_0_IResourceMetadataSectionResourceMetadataSectionTypeRegistryString(
			ResourceMetadataSectionTypeRegistry var1, String var2) throws ZipException, FileNotFoundException, ResourceNotFoundException, IOException {
		
		System.out.println("WTFResourcePack: requested metadata section '"+var2+"'");
		if(var2.equals("language"))
			return Minecraft.getMinecraft().func_0_Minecraft_ResourcePackDownloader().field_0_ResourcePackDownloader_L.func_0_IResourceMetadataSectionResourceMetadataSectionTypeRegistryString(var1, var2);
		return null;
	}

	@Override
	public BufferedImage func_0_BufferedImage() throws ZipException, FileNotFoundException, ResourceNotFoundException, IOException {
		throw new FileNotFoundException("pack icon");
	}

	@Override
	public String func_19_String() {
		return "Classpath";
	}

	

}
