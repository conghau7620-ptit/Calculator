package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button number
        btn0.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (!st.isEmpty() && lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "0")
        }
        btn1.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "1")
        }
        btn2.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "2")
        }
        btn3.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "3")
        }
        btn4.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "4")
        }
        btn5.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "5")
        }
        btn6.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "6")
        }
        btn7.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "7")
        }
        btn8.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "8")
        }
        btn9.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!=')')
                tvCalculation.setText(tvCalculation.text.toString() + "9")
        }

        //button calculate
        btnPlus.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (!st.isEmpty() && lastIndex!='+' && lastIndex!='-' && lastIndex!='*' &&
                    lastIndex!='/' && lastIndex!='.' && lastIndex!='(')
                tvCalculation.setText(tvCalculation.text.toString() + "+")
        }
        btnMinus.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (lastIndex!='+' && lastIndex!='-' && lastIndex!='*' &&
                lastIndex!='/' && lastIndex!='.')
            tvCalculation.setText(tvCalculation.text.toString() + "-")
        }
        btnMultiply.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (!st.isEmpty() && lastIndex!='+' && lastIndex!='-' && lastIndex!='*' &&
                lastIndex!='/' && lastIndex!='.' && lastIndex!='(')
                tvCalculation.setText(tvCalculation.text.toString() + "*")
        }
        btnDivide.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (!st.isEmpty() && lastIndex!='+' && lastIndex!='-' && lastIndex!='*' &&
                lastIndex!='/' && lastIndex!='.' && lastIndex!='(')
                tvCalculation.setText(tvCalculation.text.toString() + "/")
        }

        //another button
        btnDot.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) "" else st.get(st.length-1)
            if (!st.isEmpty() && lastIndex.hashCode()>=48 &&
                    lastIndex.hashCode()<=57)
                tvCalculation.setText(tvCalculation.text.toString() + ".")
        }

        btnAllClear.setOnClickListener{
            tvCalculation.setText("")
            tvResult.setText("")
        }

        btnClear.setOnClickListener{
            var st = tvCalculation.text.toString()
            if (!st.isEmpty())
            tvCalculation.setText(st.substring(0,st.length-1))
        }

        btnParenthese1.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) 0 else st.get(st.length-1)
            if (st.isEmpty() || lastIndex=='+' || lastIndex=='-' ||
                    lastIndex=='*' || lastIndex=='/') {
                tvCalculation.setText(st + "(")
            }
        }

        btnParenthese2.setOnClickListener{
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) 0 else st.get(st.length-1)
            if (lastIndex.hashCode()>=48 && lastIndex.hashCode()<=57) {
                tvCalculation.setText(st+")")
            }
        }

        //button equal
        btnEqual.setOnClickListener{
            //catch exception
            var st = tvCalculation.text.toString()
            var lastIndex = if (st.isEmpty()) 0 else st.get(st.length-1)
            while (lastIndex=='+' || lastIndex=='-' || lastIndex=='*' || lastIndex=='/' ||
                    lastIndex=='.' || lastIndex=='(') {
                st = st.substring(0,st.length-1)
                lastIndex = if (st.isEmpty()) 0 else st.get(st.length-1)
            }
            var count1 = countChar(st,'(')
            var count2 = countChar(st,')')
            if (count1>count2) {
                for (i in 1..count1-count2) {
                    st = st+')'
                }
            } else if (count1<count2) {
                for (i in 1..count2-count1) {
                    st = '(' + st
                }
            }
            tvCalculation.setText(st)

            //calculate
            while (findCharRTL(st,'(')!=-1) {
                var begin = findCharRTL(st,'(')
                var end = begin + findCharLTR(st.substring(begin+1),')') +1
                var st1 = st.substring(begin, end+1)
                st = st.replace(st1,calculate(st1.substring(1,st1.length-1)))
            }
            st = calculate(st)
            if (st.substring(st.length-2)==".0")
                st = st.substring(0,st.length-2)
            tvResult.setText(st)

        }

    }

    fun countChar(st: String, ch: Char) :Int {
        var c=0;
        for (i in 0..st.length-1) {
            if (st.get(i)==ch) {
                c++;
            }
        }
        return c;
    }

    fun findCharLTR(st: String, ch: Char) :Int {
        for (i in 0 .. st.length-1) {
            if (st.get(i)==ch) {
                return i
            }
        }
        return -1
    }

    fun findCharRTL(st: String, ch: Char) :Int {
        for (i in st.length-1 downTo 0) {
            if (st.get(i)==ch) {
                return i
            }
        }
        return -1
    }

    fun plus(st: String) :String{
        var pos = findCharLTR(st,'+')
        var num1 = st.substring(0,pos).toDouble()
        var num2 = st.substring(pos+1).toDouble()
        return (num1+num2).toString()
    }

    fun minus(st: String) :String{
        var pos = findCharLTR(st,'-')
        var num1 = st.substring(0,pos).toDouble()
        var num2 = st.substring(pos+1).toDouble()
        return (num1-num2).toString()
    }

    fun multiply(st: String) :String{
        var pos = findCharLTR(st,'*')
        var num1 = st.substring(0,pos).toDouble()
        var num2 = st.substring(pos+1).toDouble()
        var rs = (num1*num2).toString()
        return rs
    }

    fun divide(st: String) :String{
        var pos = findCharLTR(st,'/')
        var num1 = st.substring(0,pos).toDouble()
        var num2 = st.substring(pos+1).toDouble()
        if (num2==0.0) {
            return "math error"
        }
        return (num1/num2).toString()
    }

    fun calculate(st: String) :String {
        var st1 = st
        var l: Int
        var r: Int

        //multiply
        var pos = findCharLTR(st1, '*')
        while (pos!=-1) {
            l = pos - 1
            while (l >= 0 && (st1.get(l).hashCode() >= 48 && st1.get(l).hashCode() <= 57 ||
                        st1.get(l) == '.')
            ) {
                l--;
            }
            if (l >= 0 && st1.get(l) == '-') l--
            r = pos + 1
            if (st1.get(r) == '-') r++;
            while (r < st1.length && (st1.get(r).hashCode() >= 48 && st1.get(r).hashCode() <= 57
                        || st1.get(r) == '.')
            ) {
                r++;
            }
            var st2 = st1.substring(l + 1, r)
            if (l>=0 && st1.get(l).hashCode()>=48 && st1.get(l).hashCode()<=57)
                st1 = st1.replace (st2, "+" + multiply(st2))
            else
                st1 = st1.replace(st2, multiply(st2))

            pos = findCharLTR(st1, '*')
        }

        //divide
        pos = findCharLTR(st1, '/')
        while (pos!=-1) {
            l = pos-1
            while (l >= 0 && (st1.get(l).hashCode() >= 48 && st1.get(l).hashCode() <= 57 ||
                        st1.get(l) == '.')
            ) {
                l--;
            }
            if (l >= 0 && st1.get(l) == '-') l--
            r = pos + 1
            if (st1.get(r) == '-') r++
            while (r < st1.length && (st1.get(r).hashCode() >= 48 && st1.get(r).hashCode()<= 57
                        || st1.get(r) == '.')) {
                r++
            }
            var st2 = st1.substring(l + 1, r)
            var st3 = divide(st2)
            if (!st3.equals("math error")) {
                if (l>=0 && st1.get(l).hashCode() >= 48 && st1.get(l).hashCode() <= 57)
                    st1 = st1.replace(st2, "+" + st3)
                else
                    st1 = st1.replace(st2, st3)
            }
            else {
                return "math error"
            }
            pos = findCharLTR(st1, '/')
        }

        //plus
        pos = findCharLTR(st1, '+')
        var pos1 = findCharRTL(st1, '-')
        while (pos!=-1 || pos1>0) {
            st1 = st1.replace("--","+")
            st1 = st1.replace("-","+-")
            if (st1.get(0)=='+') st1 = st1.substring(1)
            st1 = st1.replace("++","+")
            pos = findCharLTR(st1,'+')

            l = pos-1
            while (l >= 0 && (st1.get(l).hashCode() >= 48 && st1.get(l).hashCode() <= 57 ||
                        st1.get(l) == '.')
            ) {
                l--;
            }
            if (l >= 0 && st1.get(l) == '-') l--
            r = pos + 1
            if (st1.get(r) == '-') r++
            while (r < st1.length && (st1.get(r).hashCode() >= 48 && st1.get(r).hashCode()<= 57
                        || st1.get(r) == '.')) {
                r++
            }
            var st2 = st1.substring(l + 1, r)

            if (l>=0 && st1.get(l).hashCode()>=48 && st1.get(l).hashCode()<=57)
                st1 = st1.replace (st2, "+" + plus(st2))
            else
                st1 = st1.replace(st2,plus(st2))

            pos = findCharLTR(st1, '+')
            pos1 = findCharRTL(st1, '-')
        }


        return st1
    }
}
