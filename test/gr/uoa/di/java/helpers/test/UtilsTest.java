package gr.uoa.di.java.helpers.test;

import static gr.uoa.di.java.helpers.Utils.listFromArray;
import gr.uoa.di.java.helpers.Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import junit.framework.Assert;

import org.apache.http.util.EncodingUtils;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void test_listToString() throws UnsupportedEncodingException {
		Assert.assertEquals("hello", Utils.listToString(Arrays.asList(
			(byte) 'h', (byte) 'e', (byte) 'l', (byte) 'l', (byte) 'o'),
			Utils.ASCII));
	}

	@Test
	public void test_listToLong() throws NumberFormatException,
			UnsupportedEncodingException {
		Assert.assertEquals(178899898, Utils
				.listToLong(listFromArray((EncodingUtils
						.getAsciiBytes("178899898")))));
	}

	@Test
	public void test_listToDouble() throws NumberFormatException,
			UnsupportedEncodingException {
		Assert.assertEquals(1788.9898, Utils
				.listToDouble(listFromArray(EncodingUtils
						.getAsciiBytes("1788.9898"))));
	}
	// crap - if I pass integers in I get ClassCastEx
	// private <T> List<Byte> castToByte(T... args) {
	// final List<Byte> li = new ArrayList<Byte>();
	// for (T t : args) {
	// li.add((Byte) t);
	// }
	// return li;
	// }
}
