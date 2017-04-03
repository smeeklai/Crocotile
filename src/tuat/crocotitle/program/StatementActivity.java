package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * The responsibilities of this class are controlling and managing content in the
 * statement tab
 */
public class StatementActivity extends Activity {
	Note note;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.state_layout);
		TestNote tn = (TestNote)((Bundle)getIntent().getExtras()).getSerializable("testNote");
		note = (Note)tn.getNote();
		Button ifButton = (Button)findViewById(R.id.if_button);
		ifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.IFTILE);
            }
        });
		Button repeatButton = (Button)findViewById(R.id.repeat_button);
		repeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.REPEATTILE);
            }
        });
		Button printButton = (Button)findViewById(R.id.print_button);
		printButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.PRINTTILE);
            }
        });
	}
}
