package gr.uoa.di.java.helpers.test;

import static gr.uoa.di.java.helpers.Utils.listFromArray;
import gr.uoa.di.java.helpers.Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.util.EncodingUtils;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

	private static final String UNKNOWN_ENCODING = "UnknownEncoding";

	@Test
	public void test_listToString() throws UnsupportedEncodingException {
		Assert.assertEquals("hello", Utils.listToString(Arrays.asList(
			(byte) 'h', (byte) 'e', (byte) 'l', (byte) 'l', (byte) 'o'),
			Utils.ASCII));
	}

	@Test
	public void test_listToString_Empty() throws UnsupportedEncodingException {
		final Byte[] ba = new Byte[0];
		final List<Byte> asList = Arrays.asList(ba);
		Assert.assertTrue(asList.isEmpty());
		Assert.assertEquals("", Utils.listToString(asList, UNKNOWN_ENCODING));
	}

	@Test(expected = UnsupportedEncodingException.class)
	public void test_listToString_UnsupportedEncoding()
			throws UnsupportedEncodingException {
		System.out.println(Utils.listToString(
			Arrays.asList((byte) 'h', (byte) 'e'), UNKNOWN_ENCODING));
	}

	/** BEHAVES AS NOTHING IS WRONG - need Java7 to make it throw */
	@Test
	public void test_listToString_UnsupportedCharacters()
			throws UnsupportedEncodingException {
		final Byte[] ba = new Byte[256];
		{
			byte b = Byte.MIN_VALUE;
			for (int i = 0; i < 256; ++i) {
				System.out.println(b);
				ba[i] = b++;
			}
		}
		System.out.println(Utils.listToString(Arrays.asList(ba), Utils.ASCII));
	}

	@Test
	public void test_listToLong() throws NumberFormatException {
		Assert.assertEquals(178899898,
			Utils.listToLong(listFromArray((EncodingUtils
				.getAsciiBytes("178899898")))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToLong_SUFFIX1() throws NumberFormatException {
		Assert.assertEquals(178899898, Utils
			.listToLong(listFromArray((EncodingUtils
				.getAsciiBytes("178899898l")))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToLong_SUFFIX2() throws NumberFormatException {
		Assert.assertEquals(178899898, Utils
			.listToLong(listFromArray((EncodingUtils
				.getAsciiBytes("178899898L")))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listEmptyToLong() throws NumberFormatException {
		Assert.assertEquals(0, Utils.listToLong(new ArrayList<Byte>()));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToLong_NumberFormatEx() throws NumberFormatException {
		Utils.listToLong(listFromArray((EncodingUtils
			.getAsciiBytes("1788@99898"))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToLong_NumberFormatEx2() throws NumberFormatException {
		Utils.listToLong(listFromArray((EncodingUtils
			.getAsciiBytes("1788.99898"))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToLong_NumberFormatEx3() throws NumberFormatException {
		Utils.listToLong(listFromArray((EncodingUtils
			.getAsciiBytes("1788,99898"))));
	}

	/* 1e1 2. .3 0.0 3.14 1e-9d 1e137 ---- More to try (from the JLS) */
	@Test
	public void test_listToDouble() throws NumberFormatException {
		Assert.assertEquals(1788.9898, Utils
			.listToDouble(listFromArray(EncodingUtils
				.getAsciiBytes("1788.9898"))), Double.MIN_VALUE);
	}

	@Test
	public void test_listToDouble2() throws NumberFormatException {
		Assert.assertEquals(1788e98,
			Utils.listToDouble(listFromArray(EncodingUtils
				.getAsciiBytes("1788e98"))), Double.MIN_VALUE);
	}

	@Test
	public void test_listToDouble3() throws NumberFormatException {
		Assert.assertEquals(1788E98,
			Utils.listToDouble(listFromArray(EncodingUtils
				.getAsciiBytes("1788E98"))), Double.MIN_VALUE);
	}

	@Test
	public void test_listToDouble4() throws NumberFormatException {
		Assert.assertEquals(73.2, Utils
			.listToDouble(listFromArray(EncodingUtils.getAsciiBytes("+73.2"))),
			Double.MIN_VALUE);
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToDouble_SUFFIX1() throws NumberFormatException {
		Assert.assertEquals(1, Utils.listToDouble(listFromArray(EncodingUtils
			.getAsciiBytes("1d"))), Double.MIN_VALUE);
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToDouble_SUFFIX2() throws NumberFormatException {
		Assert.assertEquals(-1, Utils.listToDouble(listFromArray(EncodingUtils
			.getAsciiBytes("-1D"))), Double.MIN_VALUE);
	}

	@Test(expected = NumberFormatException.class)
	public void test_listEmptyToDouble() throws NumberFormatException {
		Assert.assertEquals(0, Utils.listToDouble(new ArrayList<Byte>()),
			Double.MIN_VALUE);
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToDouble_NumberFormatEx1()
			throws NumberFormatException {
		Utils.listToDouble(listFromArray((EncodingUtils
			.getAsciiBytes("17885oio99898"))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToDouble_NumberFormatEx2()
			throws NumberFormatException {
		Utils.listToDouble(listFromArray((EncodingUtils
			.getAsciiBytes("17884*99898"))));
	}

	@Test(expected = NumberFormatException.class)
	public void test_listToDouble_NumberFormatEx3()
			throws NumberFormatException {
		Utils.listToDouble(listFromArray((EncodingUtils
			.getAsciiBytes("1788()99898"))));
	}
}
