package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * The responsibilities of this class are controlling and managing content in the
 * variable tab
 */
public class VariableActivity extends Activity {
	String test = "Test int button";
	Note note;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.var_layout);
		TestNote tn = (TestNote)((Bundle)getIntent().getExtras()).getSerializable("testNote");
		note = (Note)tn.getNote();
		Button intButton = (Button) findViewById(R.id.int_button);
		intButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.VARIABLEINTTILE);
            }
        });
		Button floatButton = (Button) findViewById(R.id.float_button);
		floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.VARIABLEFLOATTILE);
            }
        });
		Button charButton = (Button) findViewById(R.id.char_button);
		charButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.VARIABLECHARTILE);
            }
        });
		Button booleanButton = (Button) findViewById(R.id.boolean_button);
		booleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.VARIABLEBOOLEANTILE);
            }
        });
		Button assignButton = (Button) findViewById(R.id.assignment_button);
		assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.ASSIGNTILE);
            }
        });

	}
}
