package tuat.crocotitle.program;

import java.io.Serializable;

public class TestNote implements Serializable {
	Note note;
	public TestNote(Note mNote) {
		note = mNote;
		// TODO Auto-generated constructor stub
	}
	public Note getNote(){
		return note;
	}
}
