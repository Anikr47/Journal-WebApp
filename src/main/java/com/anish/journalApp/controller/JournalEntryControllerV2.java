package com.anish.journalApp.controller;
import com.anish.journalApp.entity.JournalEntry;
import com.anish.journalApp.entity.User;
import com.anish.journalApp.service.JournalEntryService;
import com.anish.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal Entry APIs", description = "Create, Read, Update and Delete journal entries")
public class JournalEntryControllerV2 {

@Autowired
private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
    @GetMapping()
    @Operation(summary = "get all journal entries of a user")
    public ResponseEntity<?> getAllJournalEntriesOfUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    @Operation(summary = "create a journal entry for user")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry my_entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(my_entry, userName);
            return new ResponseEntity<>(my_entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    @Operation(summary = "get a particular journal entry by ID of user")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    @Operation(summary = "delete a particular journal entry by ID of user")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(objectId, userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/id/{myId}")
    @Operation(summary = "update a existing journal entry for a user")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable String myId, @RequestBody JournalEntry new_entry) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if(journalEntry.isPresent()){
                    JournalEntry old = journalEntry.get();
                    old.setTitle(new_entry.getTitle() != null && !new_entry.getTitle().equals("") ? new_entry.getTitle() : old.getTitle());
                    old.setContent(new_entry.getContent() != null && !new_entry.getContent().equals("") ? new_entry.getContent() : old.getContent());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
