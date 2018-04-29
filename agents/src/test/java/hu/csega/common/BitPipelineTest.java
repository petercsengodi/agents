package hu.csega.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.csega.common.BitPipeline;


public class BitPipelineTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		BitPipeline bp = new BitPipeline();

		try {
			bp.readBoolean();
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}

		bp.set(1);
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertFalse(bp.readBoolean());
		assertTrue(bp.readBoolean());

		try {
			bp.readBoolean();
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}
	}

	@Test
	public void testByteMinus1() {
		BitPipeline bp = new BitPipeline();

		byte b = (byte)-1;

		bp.set(b);
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());

		try {
			bp.readBoolean();
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}
	}

	@Test
	public void testByteMinus2() {
		BitPipeline bp = new BitPipeline();

		byte b = (byte)-2;

		bp.set(b);
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertTrue(bp.readBoolean());
		assertFalse(bp.readBoolean());

		try {
			bp.readBoolean();
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}
	}

	@Test
	public void testStream() {
		BitPipeline bp = new BitPipeline(3);

		int testByte = 2 + 8 + 32 + 128; // 10101010

		// put in 16 bits, read 10 -> 6 remaining
		bp.add(testByte);
		bp.add(testByte);

		for(int i = 0; i < 10; i++) {
			Assert.assertEquals("Error in bit: " + i, (i % 2) == 0, bp.readBoolean());
		}

		// put in 16 bits, read 10 -> 12 remaining
		bp.add(testByte);
		bp.add(testByte);

		for(int i = 0; i < 10; i++) {
			Assert.assertEquals("Error in bit: " + i, (i % 2) == 0, bp.readBoolean());
		}


		// read 12 remaining
		for(int i = 0; i < 12; i++) {
			Assert.assertEquals("Error in bit: " + i, (i % 2) == 0, bp.readBoolean());
		}

		// should be out of bits
		try {
			bp.readBoolean();
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}

		// should be too much bytes
		bp.clear();
		bp.add(testByte);
		bp.add(testByte);
		bp.add(testByte);
		try {
			bp.add(testByte);
			Assert.fail("Should throw error, since we are out of bits!");
		} catch(IllegalStateException ex) {
			// hooray!
		}

	}

}
