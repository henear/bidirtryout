package com.example.bidirtryout;

import com.example.bidirtryout.firstutil.MyCollectionUtils;
import com.example.bidirtryout.secondutil.MyStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


public class BidirtryoutApplicationTests {

	@Test
	public void checkStringEmptyHandlesNullAndEmpty() {
		assertTrue(MyStringUtils.checkStringEmpty(null));
		assertTrue(MyStringUtils.checkStringEmpty(""));
		assertFalse(MyStringUtils.checkStringEmpty("abc"));
	}

	@Test
	public void checkCollectionEmptyHandlesNullAndLists() {
		assertFalse(MyCollectionUtils.checkCollectionEmpty(null));
		assertTrue(MyCollectionUtils.checkCollectionEmpty(List.of()));
		assertFalse(MyCollectionUtils.checkCollectionEmpty(List.of("x")));
	}

	@Test
	public void utilityMethodsKeepExpectedSemantics() {
		assertEquals("hello", MyStringUtils.checkStringEmpty("") ? "hello" : "world");
		assertTrue(MyCollectionUtils.checkCollectionEmpty(List.of()));
	}

	@Test
	public void dummyTest() {
		assertEquals(5, 2 + 3);
	}
}
