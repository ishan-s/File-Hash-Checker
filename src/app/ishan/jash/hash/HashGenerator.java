package app.ishan.jash.hash;

import java.io.File;
import java.io.IOException;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import app.ishan.jash.common.Const;

public class HashGenerator {
	String hashType;

	public HashGenerator(){
		
	}
	public HashGenerator(String hashType){
		this.hashType = hashType;
	}
	
	public String generateHash(File file) throws IOException{
		return generateHash(file, hashType);
	}
	
	public String generateHash(File file, String hashType) throws IOException{
		if(hashType==null)
			throw new RuntimeException("Hash type not set");
		
		if(file==null || !file.exists() || file.isDirectory())
			throw new IOException("Invalid file passed");
		
		HashCode hc = Files.hash(file, getHashFunction(hashType));
		return hc.toString();
	}
	
	private HashFunction getHashFunction(String hashType) {
		if(hashType.equals(Const.SUPPORTED_HASH_TYPES[0])) //MD5
			return Hashing.md5();
		else if(hashType.equals(Const.SUPPORTED_HASH_TYPES[1])) //CRC32
			return Hashing.crc32();
		else if(hashType.equals(Const.SUPPORTED_HASH_TYPES[2])) //CRC32C
			return Hashing.crc32c();
		else
			throw new IllegalArgumentException("Unsupported Hash Type");
	}

}
