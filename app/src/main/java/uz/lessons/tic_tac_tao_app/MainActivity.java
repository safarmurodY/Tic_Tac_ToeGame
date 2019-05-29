package uz.lessons.tic_tac_tao_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private boolean x = true;
    private Button[][] buttons;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_tou_layout);
        LinearLayout condtainer = findViewById(R.id.container);
        buttons = new Button[condtainer.getChildCount()][];
        for (int i = 0; i < condtainer.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) condtainer.getChildAt( i);
            buttons[i] = new Button[row.getChildCount()];
            for (int j = 0; j <row.getChildCount() ; j++) {
                Button btn = (Button) row.getChildAt(j);
                buttons[i][j] = btn;
                btn.setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        if (x){
            btn.setText("X");
        } else{
            btn.setText("O");
        }
        x=!x;
        btn.setEnabled(false);
        count++;
        check();
    }
    private void check(){

        for (int i = 0; i < 3; i++) {
            int rowcount = 0, colcount = 0;
            for (int j = 0; j <3 ; j++) {
                if(buttons[i][j].getText().equals("X")){rowcount++;}
                if (buttons[j][i].getText().equals("X")){colcount++;}
            }
            if(rowcount == 3 || colcount == 3){
                win(1);
                return;
            }
        }
        int count1 =0;
        int count2 = 0;
        for (int i = 0; i <3 ; i++) {
            if(buttons[i][i].getText().equals("X")){count1++;}
            if(buttons[3-i-1][i].getText().equals("X")){count2++;}
        }
        if (count1 == 3 || count2 == 3){
            win(1);
        }
        for (int i = 0; i < 3; i++) {
            int rowcount = 0;
            int colcount = 0;
            for (int j = 0; j <3 ; j++) {
                if(buttons[i][j].getText().equals("O")){rowcount++;}
                if (buttons[j][i].getText().equals("O")){colcount++;}
            }
            if(rowcount == 3 || colcount == 3){
                win(0);
                return;
            }
        }
        count1 =0;
        count2 = 0;
        for (int i = 0; i <3 ; i++) {
            if(buttons[i][i].getText().equals("O")){count1++;}
            if(buttons[3-i-1][i].getText().equals("O")){count2++;}
        }
        if (count1 == 3 || count2 == 3){
            win(0);
            return;
        }
        if (count == 9){
            win(2);
        }
    }
    private void win(int res){
        count = 0;

        String result;
        if(res == 0){
            result = "O YUTDI";
        }
        else if(res == 1){
            result = "X YUTDI";
        }
        else {
            result = "D U R A N G";
        }
        Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);

            }
        }
    }

}
