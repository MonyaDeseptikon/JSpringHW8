package service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import aspects.TrackUserAction;
import model.Note;
import repository.NoteRepo;
import service.NoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;

    @Override
    @TrackUserAction
    public Note createNote(Note note) {
        return noteRepo.save(note);
    }

    @Override
    @TrackUserAction
    public List<Note> getAllNotes() {
        return noteRepo.findAll();
    }

    @Override
    @TrackUserAction
    public Note getNoteById(Long id) {
        return noteRepo.findById(id).orElseThrow(null);
    }

    @Override
    @TrackUserAction
    public Note updateNote(Note note) {
        Note noteByID = getNoteById(note.getId());

        noteByID.setTitle(note.getTitle());
        noteByID.setContent(note.getContent());

        return noteRepo.save(noteByID);
    }

    @Override
    @TrackUserAction
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepo.delete(noteById);
    }
}
