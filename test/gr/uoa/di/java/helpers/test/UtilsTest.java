package gr.uoa.di.java.helpers.test;

import gr.uoa.di.java.helpers.Utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void test_listToString() throws UnsupportedEncodingException {
		Assert.assertEquals("hello", Utils.listToString(Arrays.asList(
			(byte) 'h', (byte) 'e', (byte) 'l', (byte) 'l', (byte) 'o'),
			"US-ASCII"));
	}

	@Test
	public void test_listToLong() {
		Assert.assertEquals(178899898, Utils.listToLong(Arrays.asList((byte) 1,
			(byte) 7, (byte) 8, (byte) 8, (byte) 9, (byte) 9, (byte) 8,
			(byte) 9, (byte) 8)));
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
