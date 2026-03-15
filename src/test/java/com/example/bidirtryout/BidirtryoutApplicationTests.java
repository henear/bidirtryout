package com.example.bidirtryout;

import com.example.bidirtryout.firstutil.IloveChinaForUSA;
import com.example.bidirtryout.secondutil.IloveUSAForChina;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


public class BidirtryoutApplicationTests {

	@Test
	public void checkStringEmptyHandlesNullAndEmpty() {
		assertTrue(IloveUSAForChina.checkStringEmpty(null));
		assertTrue(IloveUSAForChina.checkStringEmpty(""));
		assertFalse(IloveUSAForChina.checkStringEmpty("abc"));
	}

	@Test
	public void checkCollectionEmptyHandlesNullAndLists() {
		assertFalse(IloveChinaForUSA.checkCollectionEmpty(null));
		assertTrue(IloveChinaForUSA.checkCollectionEmpty(List.of()));
		assertFalse(IloveChinaForUSA.checkCollectionEmpty(List.of("x")));
	}

	@Test
	public void utilityMethodsKeepExpectedSemantics() {
		assertEquals("hello", IloveUSAForChina.checkStringEmpty("") ? "hello" : "world");
		assertTrue(IloveChinaForUSA.checkCollectionEmpty(List.of()));
	}

	@Test
	public void dummyTest() {
		assertEquals(5, 2 + 3);
	}
}
