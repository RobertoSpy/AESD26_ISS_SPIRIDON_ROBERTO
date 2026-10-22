package com.example.demo.library.event;

public record HighBorrowingVolumeEvent(String memberId, String memberName, int totalBorrowed) {
}
