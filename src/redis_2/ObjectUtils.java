package redis_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * ���󹤾���
 * @author Administrator
 *
 */
public class ObjectUtils {
	/*
	 * ������ת��Ϊ�ֽ�����
	 */
	public static byte[] objectToByte(Object obj) throws IOException{
		ByteOutputStream boas=new ByteOutputStream();
		ObjectOutputStream oops=new ObjectOutputStream(boas);
		oops.writeObject(obj);
		return boas.getBytes();
	}
	
	/*
	 * ���ֽ�����ת��Ϊ����
	 */
	public static Object byteToObject(byte[] src) throws Exception{
		ByteInputStream boas=new ByteInputStream(src,src.length);
		ObjectInputStream ois=new ObjectInputStream(boas);
		return ois.readObject();
		
	}
}
