package com.horizon.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.BreakIterator;

import timber.log.Timber;
public class MainActivity extends AppCompatActivity {
    Button btn[]=new Button[20];
    int btnIds[]={R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
            , R.id.btn_00, R.id.btn_0, R.id.btn_dot, R.id.btn_equ, R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div
            , R.id.btn_del, R.id.btn_percent, R.id.btn_delAll};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv_input=findViewById(R.id.tv_input),
                tv_output=findViewById(R.id.tv_output);
        for (int i = 0; i < 20; i++) {
            btn[i]=findViewById(btnIds[i]);
            btn[i].setOnClickListener(new BtnListener(btn[i], tv_input, tv_output, i));
        }
    }

}

class BtnListener implements View.OnClickListener{
    public enum  BtnOption{BTN1(0),BTN2(1),BTN3(2),BTN4(3),BTN5(4),BTN6(5),BTN7(6),BTN8(7),BTN9(8)
        ,BTN00(9),BTN0(10),BTNDOT(11),BTNEQU(12),BTNADD(13),BTNSUB(14),BTNMUL(15),BTNDIV(16)
        ,BTNDEL(17),BTNPERCENT(18),BTNDELALL(19), NULL(-1);

        private final int i;
        BtnOption(int i) {
            this.i = i;
        }

        public static BtnOption valueOf(int i) {
            switch (i) {
                case 0: return BTN1;
                case 1: return BTN2;
                case 2: return BTN3;
                case 3: return BTN4;
                case 4: return BTN5;
                case 5: return BTN6;
                case 6: return BTN7;
                case 7: return BTN8;
                case 8: return BTN9;
                case 9: return BTN00;
                case 10: return BTN0;
                case 11: return BTNDOT;
                case 12: return BTNEQU;
                case 13: return BTNADD;
                case 14: return BTNSUB;
                case 15: return BTNMUL;
                case 16: return BTNDIV;
                case 17: return BTNDEL;
                case 18: return BTNPERCENT;
                case 19: return BTNDELALL;
            }
            return NULL;
        }

        public int getI() {
            return this.i;
        }
    }
    BtnOption mOption;
    int mOptionNum;
    TextView tv_input, tv_output;
    //字符串需要全部保存，然后做一个滑动窗口。
    boolean isStart=true;

    String mStrInput;
    String mStrOutput;

    private void optionInput(){
        switch (mOption){
            case BTN1:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1); } mStrInput+="1"; break;
            case BTN2:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1); } mStrInput+="2"; break;
            case BTN3:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="3"; break;
            case BTN4:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="4"; break;
            case BTN5:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="5"; break;
            case BTN6:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="6"; break;
            case BTN7:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="7"; break;
            case BTN8:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="8"; break;
            case BTN9:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="9"; break;
            case BTN00:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="00"; break;
            case BTN0:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="0"; break;
            case BTNDOT:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="."; break;
            //case BTNEQU: if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(0, mStrInput.length()-1);mStrInput+="="; break;
            case BTNADD:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="+"; break;
            case BTNSUB:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="-"; break;
            case BTNMUL:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="*"; break;
            case BTNDIV:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="/"; break;
            case BTNDEL:
                if(mStrInput.length()>=1) mStrInput = mStrInput.substring(0, mStrInput.length()-1); break;
            case BTNPERCENT:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput+="%"; break;
            case BTNDELALL:
                if(mStrInput.length()>=13){ mStrInput = mStrInput.substring(1);} mStrInput=""; break;
            default: break;
        }
    }
    private void optionOutput(){
        switch (mOption){
            case BTN1: //mStrInput+="1"; break;
            case BTN2: //mStrInput+="2"; break;
            case BTN3: //mStrInput+="3"; break;
            case BTN4: //mStrInput+="4"; break;
            case BTN5: //mStrInput+="5"; break;
            case BTN6: //mStrInput+="6"; break;
            case BTN7: //mStrInput+="7"; break;
            case BTN8: //mStrInput+="8"; break;
            case BTN9: //mStrInput+="9"; break;
            case BTN00:// mStrInput+="00"; break;
            case BTN0: //mStrInput+="0"; break;
            case BTNDOT:// mStrInput+="."; break;
                break;
            case BTNEQU: mStrOutput="123"; break;
//            case BTNADD: mStrInput+="+"; break;
            //          case BTNSUB: mStrInput+="-"; break;
            //        case BTNMUL: mStrInput+="*"; break;
            //      case BTNDIV: mStrInput+="/"; break;
            //    case BTNDEL: mStrInput = mStrInput.substring(0, mStrInput.length()-1); break;
            //  case BTNPERCENT: mStrInput+="%"; break;
            case BTNDELALL: mStrOutput=""; break;
            default: break;
        }
    }

    private Button btn;
    BtnListener(Button btn, TextView tv_input, TextView tv_output, int optionNum){
        this.btn=btn;
        this.tv_input=tv_input;
        this.tv_output=tv_output;
        mOptionNum=optionNum;
    }
    @Override
    public void onClick(View view) {
        mStrInput=String.valueOf(tv_input.getText());
        mStrOutput=String.valueOf(tv_output.getText());
        mOption=BtnOption.valueOf(mOptionNum);
        optionInput();
        optionOutput();
        tv_input.setText(mStrInput);
        tv_output.setText(mStrOutput);
    }
}