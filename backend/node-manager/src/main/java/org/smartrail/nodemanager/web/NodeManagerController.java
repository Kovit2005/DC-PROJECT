package org.smartrail.nodemanager.web;

import org.smartrail.nodemanager.service.ElectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/nodes")
public class NodeManagerController {
    private final ElectionService electionService;

    public NodeManagerController(ElectionService electionService) {
        this.electionService = electionService;
    }

    @GetMapping("/leader")
    public ResponseEntity<?> leader() {
        return ResponseEntity.ok(Map.of("leader", electionService.getCurrentLeader()));
    }
}
