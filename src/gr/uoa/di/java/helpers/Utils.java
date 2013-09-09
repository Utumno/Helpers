package gr.uoa.di.java.helpers;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

public final class Utils {

	// Standard charsets' NAMES
	// Seven-bit ASCII, a.k.a. ISO646-US, a.k.a. the Basic Latin block of the
	// Unicode character set
	public static final String ASCII = "US-ASCII";
	// ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1
	public static final String ISO8859 = "ISO-8859-1";
	// Eight-bit UCS Transformation Format
	public static final String UTF8 = "UTF-8";
	// Sixteen-bit UCS Transformation Format, big-endian byte order
	public static final String UTF16BE = "UTF-16BE";
	// Sixteen-bit UCS Transformation Format, little-endian byte order
	public static final String UTF16LE = "UTF-16LE";
	// Sixteen-bit UCS Transformation Format, byte order identified by an
	// optional byte-order mark
	public static final String UTF16 = "UTF-16";

	private Utils() {}

	public static String listToString(List<Byte> lb, String charsetName)
			throws UnsupportedEncodingException {
		// TODO better tests
		if (lb == null)
			throw new NullPointerException("List<Byte> can't be null");
		byte[] array = new byte[lb.size()];
		{
			int i = 0;
			for (byte current : lb) {
				array[i] = current;
				i++;
			}
		}
		return new String(array, charsetName);
	}

	public static long listToLong(List<Byte> lb) {
		// TODO better tests
		if (lb == null)
			throw new NullPointerException("List<Byte> can't be null");
		long result = 0;
		long i = 1;
		for (int j = lb.size() - 1; j >= 0; --j) {
			byte b = lb.get(j);
			result += b * i;
			i *= 10L;
		}
		// crap
		// byte[] array = new byte[lb.size()];
		// int i = 0;
		// for (byte current : lb) {
		// array[i] = current;
		// i++;
		// }
		// ByteBuffer wrapped = ByteBuffer.wrap(array); // big-endian by default
		// return wrapped.getLong();
		return result;
	}

	/**
	 * If list is empty 0 is returned. If List is null is thrown
	 *
	 * @param lb
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NullPointerException
	 *             if ld is null
	 */
	public static double listToDouble(List<Byte> lb) throws UnsupportedEncodingException {
		// TODO better tests
		if (lb == null)
			throw new NullPointerException("List<Byte> can't be null");
		return new BigDecimal(listToString(lb, ASCII)).doubleValue();
	}
}
