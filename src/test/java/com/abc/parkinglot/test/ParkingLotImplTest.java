package com.abc.parkinglot.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.abc.parkinglot.exception.ParkingLotException;
import com.abc.parkinglot.impl.ParkingLotImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingLotImplTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void test00GetInstance() {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		Assert.assertNotNull(impl);
	}
	
	@Test
	public void test01InitalizeSuccess() {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			impl.initialize(4);
		} catch (ParkingLotException ple) {
			Assert.fail(ple.getMessage());
		}
	}
	
	@Test
	public void test02InitlizeFailure() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();	
		
		impl.initialize(4);
		
		exception.expect(ParkingLotException.class);
		//Initializing again should fail.
		impl.initialize(10);		
	}
	
	@Test
	public void test03ParkSuccess() {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			impl.initialize(3);
			impl.park("KA-01-HH-1234", "White");
		} catch (ParkingLotException ple) {
			Assert.fail(ple.getMessage());
		}
	}
	
	@Test
	public void test04ParkFailure() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		impl.initialize(3);
		impl.park("KA-01-HH-1234", "White");
		impl.park("KA-01-HH-9999", "White");
		impl.park("KA-01-BB-0001", "Block");
		
		exception.expect(ParkingLotException.class);
		impl.park("KA-01-HH-2701", "Blue");
	}
	
	@Test
	public void test05LeaveSuccess() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		impl.initialize(3);
		impl.park("KA-01-HH-1234", "White");
		impl.park("KA-01-HH-9999", "White");
		impl.park("KA-01-BB-0001", "Block");
		
		impl.leave(2);
		int slots = impl.status();
		Assert.assertEquals(slots, 2);		
	}
	
	@Test
	public void test06LeaveFailure() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		impl.initialize(3);
		impl.park("KA-01-HH-1234", "White");
		impl.park("KA-01-HH-9999", "White");
		impl.park("KA-01-BB-0001", "Block");
		
		impl.leave(2);
		
		exception.expect(ParkingLotException.class);
		impl.leave(2);
	}
	
	@Test
	public void test07TestStatusSuccess() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		impl.initialize(3);
		impl.park("KA-01-HH-1234", "White");
		impl.park("KA-01-HH-9999", "White");
		impl.park("KA-01-BB-0001", "Block");
		
		int slots = impl.status();
		Assert.assertEquals(slots, 3);
	}
	
	@Test
	public void test08TestStatusEmptySuccess() throws ParkingLotException {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		impl.initialize(3);
		
		int slots = impl.status();
		Assert.assertEquals(slots, 0);
	}
	
	@After
	public void afterEachTest() {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		impl.reset();
	}
}
