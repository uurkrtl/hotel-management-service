package com.eagle.hotelmanagementservice.business.concretes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.eagle.hotelmanagementservice.business.abstracts.BookingStatusService;
import com.eagle.hotelmanagementservice.business.request.BookingStatusCreateRequest;
import com.eagle.hotelmanagementservice.business.request.BookingStatusUpdateRequest;
import com.eagle.hotelmanagementservice.business.response.BookingStatusGetByIdResponse;
import com.eagle.hotelmanagementservice.business.response.BookingStatusesGetAllResponse;
import com.eagle.hotelmanagementservice.entities.BookingStatus;

@SpringBootTest
public class BookingStatusServiceTest {
	BookingStatusService mockBookingStatusService;
	
	@Test
	public void testGetAllBookingStatuses() {
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		List<BookingStatusesGetAllResponse> fakeBookingStatuses = List.of(
				new BookingStatusesGetAllResponse(100L, "completed", true),
				new BookingStatusesGetAllResponse(101L, "canceled", true),
				new BookingStatusesGetAllResponse(102L, "reserved", true),
				new BookingStatusesGetAllResponse(103L, "deleted", false)
				);
		
		when(mockBookingStatusService.getAllBookingStatuses()).thenReturn(fakeBookingStatuses);
		
		//act
		List<BookingStatusesGetAllResponse> actualBookingStatuses = mockBookingStatusService.getAllBookingStatuses();
		
		//assert
		assertEquals(fakeBookingStatuses, actualBookingStatuses);
	}
	
	@Test
	public void testGetByActiveBookingStatuses() {
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		List<BookingStatusesGetAllResponse> fakeBookingStatuses = new ArrayList<>();
		fakeBookingStatuses.add(new BookingStatusesGetAllResponse(100L, "completed", true));
		fakeBookingStatuses.add(new BookingStatusesGetAllResponse(101L, "canceled", true));
		fakeBookingStatuses.add(new BookingStatusesGetAllResponse(102L, "reserved", false));
		fakeBookingStatuses.add(new BookingStatusesGetAllResponse(103L, "deleted", true));

		when(mockBookingStatusService.getByActiveBookingStatuses(true)).thenReturn(fakeBookingStatuses);
		
		//act
		List<BookingStatusesGetAllResponse> activeBookingStatuses = mockBookingStatusService.getByActiveBookingStatuses(true);
		
		//assert
		assertEquals(4, activeBookingStatuses.size());
	}
	
	@Test
	public void testGetByIdBookingStatuses() {
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		BookingStatusGetByIdResponse fakeBookingStatus = new BookingStatusGetByIdResponse(100L, "Ugur Kartal", true);

		when(mockBookingStatusService.getByIdBookingStatus(100L)).thenReturn(fakeBookingStatus);
		
		//act
		BookingStatusGetByIdResponse activeBookingStatus = mockBookingStatusService.getByIdBookingStatus(100L);
		
		//assert
		assertEquals(100L, activeBookingStatus.getId());
		assertEquals("Ugur Kartal", activeBookingStatus.getName());
		assertEquals(true, activeBookingStatus.isActive());
	}
	
	@Test
	public void testAddBookingStatus() {
		
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		BookingStatusCreateRequest fakeBookingStatus = new BookingStatusCreateRequest("deleted");		
		when(mockBookingStatusService.addBookingStatus(fakeBookingStatus)).thenReturn(true);
		
		//act
		boolean result = mockBookingStatusService.addBookingStatus(fakeBookingStatus);
		
		//assert
		assertTrue(result);
	}
	
	@Test
	public void testUpdateBookingStatus() {
		
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		BookingStatusUpdateRequest fakeBookingStatus = new BookingStatusUpdateRequest(100L, "deleted");		
		when(mockBookingStatusService.updateBookingStatus(fakeBookingStatus)).thenReturn(true);
		
		//act
		boolean result = mockBookingStatusService.updateBookingStatus(fakeBookingStatus);
		
		//assert
		assertTrue(result);
	}
	
	@Test
	public void testmakePassiveBookingStatus() {
		
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		BookingStatus fakeBookingStatus = new BookingStatus(100L, "deleted", true);		
		when(mockBookingStatusService.makePassiveBookingStatus(100L)).thenReturn(true);
		
		//act
		boolean result = mockBookingStatusService.makePassiveBookingStatus(100L);
		
		//assert
		assertTrue(result);
	}
	
	@Test
	public void testmakeActiveBookingStatus() {
		
		//arrange
		mockBookingStatusService = mock(BookingStatusService.class);
		
		BookingStatus fakeBookingStatus = new BookingStatus(100L, "deleted", true);		
		when(mockBookingStatusService.makePassiveBookingStatus(100L)).thenReturn(true);
		
		//act
		boolean result = mockBookingStatusService.makePassiveBookingStatus(100L);
		
		//assert
		assertTrue(result);
	}

}
