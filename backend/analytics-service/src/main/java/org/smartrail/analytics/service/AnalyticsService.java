package org.smartrail.analytics.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AnalyticsService {
    @PersistenceContext
    private EntityManager em;

    private volatile Map<String, Object> cachedSummary = new HashMap<>();

    // Runs periodically to compute MapReduce-style analytics over bookings
    @Scheduled(fixedDelayString = "${analytics.delay:30000}")
    @Transactional(readOnly = true)
    public void computeSummary() {
        // Map phase: fetch bookings and map to (route -> [count, revenue])
        List<Object[]> rows = em.createQuery("select t.route, count(b), sum(b.totalPrice) from Booking b join b.train t group by t.route", Object[].class)
                .getResultList();

        Map<String, Object> out = new HashMap<>();
        for (Object[] r : rows) {
            String route = (String) r[0];
            Long count = (Long) r[1];
            BigDecimal revenue = (BigDecimal) r[2];
            Map<String, Object> v = new HashMap<>();
            v.put("bookings", count);
            v.put("revenue", revenue == null ? BigDecimal.ZERO : revenue);
            out.put(route, v);
        }

        cachedSummary = out;
    }

    public Map<String, Object> getSummary() {
        return cachedSummary;
    }
}
