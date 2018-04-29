package hu.csega.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.csega.common.BitAssembler;
import hu.csega.common.BitPipeline;

public class BitAssemblerTest {

	public BitPipeline pipeline = new BitPipeline();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		byte[] bytes = new byte[1];
		BitAssembler assembler = new BitAssembler();
		assembler.setByteArray(bytes);
		assembler.setPosition(0);

		assembler.setBitAndSlide(true);
		Assert.assertEquals("10000000", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11000000", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11100000", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11110000", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11111000", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11111100", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11111110", byteToString(bytes, 0));
		assembler.setBitAndSlide(true);
		Assert.assertEquals("11111111", byteToString(bytes, 0));

		assembler.setPosition(0);

		assembler.setBitAndSlide(false);
		Assert.assertEquals("01111111", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00111111", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00011111", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00001111", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00000111", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00000011", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00000001", byteToString(bytes, 0));
		assembler.setBitAndSlide(false);
		Assert.assertEquals("00000000", byteToString(bytes, 0));
	}

	@Test
	public void test2() {
		byte[] bytes = new byte[1];
		BitAssembler assembler = new BitAssembler();
		assembler.setByteArray(bytes);
		assembler.setPosition(0);

		assembler.setBitsAndSlide(11, 6);
		Assert.assertEquals("00101100", byteToString(bytes, 0));
	}

	private String byteToString(byte[] bytes, int position) {
		pipeline.set(bytes[position]);
		StringBuilder builder = new StringBuilder();
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		builder.append(pipeline.readBoolean() ? "1" : "0");
		return builder.toString();
	}

}
