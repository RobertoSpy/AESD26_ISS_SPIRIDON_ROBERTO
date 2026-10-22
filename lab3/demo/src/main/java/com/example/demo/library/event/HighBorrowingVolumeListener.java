package com.example.demo.library.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HighBorrowingVolumeListener {

    private static final Logger log = LoggerFactory.getLogger(HighBorrowingVolumeListener.class);

    @EventListener
    public void onHighBorrowing(HighBorrowingVolumeEvent event) {
        log.warn("[EVENT] Membrul '{}' (ID: {}) detine {} carti - volum mare de imprumuturi!",
                event.memberName(), event.memberId(), event.totalBorrowed());
    }
}
