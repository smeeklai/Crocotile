package tuat.crocotitle.program;

import com.example.testproject01.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
 * The responsibilities of this class are controlling and managing content in the
 * operator tab
 */

public class OperatorActivity extends Activity{
	Note note;
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oper_layout);
		TestNote tn = (TestNote)((Bundle)getIntent().getExtras()).getSerializable("testNote");
		note = (Note)tn.getNote();
		Button addButton = (Button)findViewById(R.id.add_button);
		addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.ADDITIONTILE);
            }
        });
		Button minusButton = (Button)findViewById(R.id.minus_button);
		minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.SUBTRACTIONTILE);
            }
        });
		Button multiplyButton = (Button)findViewById(R.id.multiply_button);
		multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.MULTIPLYTILE);
            }
        });
		Button divideButton = (Button)findViewById(R.id.divide_button);
		divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.DIVISIONTILE);
            }
        });
		Button modButton = (Button)findViewById(R.id.mod_button);
		modButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.MODULATIONTILE);
            }
        });
		Button equalButton = (Button)findViewById(R.id.equal_button);
		equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.EQUAL);
            }
        });
		Button NotEqualButton = (Button)findViewById(R.id.notEqual_button);
		NotEqualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.NOTEQUAL);
            }
        });
		Button greaterButton = (Button)findViewById(R.id.greater_button);
		greaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.GREATERTHAN);
            }
        });
		Button lowerButton = (Button)findViewById(R.id.lower_button);
		lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.LOWERTHAN);
            }
        });
		Button greaterEButton = (Button)findViewById(R.id.greaterEqual_button);
		greaterEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.GREATERTHANEQUALTO);
            }
        });
		Button lowerEButton = (Button)findViewById(R.id.lowerEqual_button);
		lowerEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note.setSelectedTileType(Note.LOWERTHANEQUALTO);
            }
        });
	}
}
