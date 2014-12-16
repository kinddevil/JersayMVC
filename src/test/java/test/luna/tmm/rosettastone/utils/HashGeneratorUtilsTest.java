package test.luna.tmm.rosettastone.utils;

import junit.framework.Assert;
import luna.tmm.rosettastone.utils.encryption.HashGeneratorUtils;

import org.junit.Test;

public class HashGeneratorUtilsTest {
	@Test
	public void HashGenTest(){
		Assert.assertEquals(null, HashGeneratorUtils.generateMD5(null));
		Assert.assertEquals("900150983CD24FB0D6963F7D28E17F72", HashGeneratorUtils.generateMD5("abc"));
		
		Assert.assertEquals(null, HashGeneratorUtils.generateSHA1(null));
		Assert.assertEquals("A9993E364706816ABA3E25717850C26C9CD0D89D", HashGeneratorUtils.generateSHA1("abc"));
		
		Assert.assertEquals(null, HashGeneratorUtils.generateSHA256(null));
		Assert.assertEquals("BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD", HashGeneratorUtils.generateSHA256("abc"));
	}
}
