package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * The responsibilities of this class are controlling and managing content in the
 * constant tab
 */
public class ConstantActivity extends Activity{
	Note note;
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.con_layout);
		TestNote tn = (TestNote)((Bundle)getIntent().getExtras()).getSerializable("testNote");
		note = (Note)tn.getNote();
		//note = (Note)((ProgramActivity)this.getApplicationCont).getNote();
		Button consInt = (Button)findViewById(R.id.cons_int_button);
		consInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.CONSTANTINTTILE);
            }
        });
		Button consFloat = (Button)findViewById(R.id.cons_float_button);
		consFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.CONSTANTFLOATTILE);
            }
        });
		Button consChar = (Button)findViewById(R.id.cons_char_button);
		consChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.CONSTANTCHARTILE);
            }
        });
		Button consBoolean = (Button)findViewById(R.id.cons_boolean_button);
		consBoolean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.CONSTANTBOOLEANTILE);
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
