
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class homework {

        private static final int MAX_SIZE = 1024;
        /** 运算符顺序栈。 */
        private static class SqStack {
            private final char[] data = new char[MAX_SIZE];
            private int top = -1;

            boolean isEmpty() {
                return top == -1;
            }

            void push(char value) {
                if (top >= data.length - 1) {
                    throw new IllegalArgumentException("运算符栈已满");
                }
                data[++top] = value;
            }

            char pop() {
                if (isEmpty()) {
                    throw new IllegalArgumentException("运算符栈为空");
                }
                return data[top--];
            }

            char peek() {
                if (isEmpty()) {
                    throw new IllegalArgumentException("运算符栈为空");
                }
                return data[top];
            }
        }

        /** 操作数顺序栈。 */
        private static class SqStack1 {
            private final double[] data = new double[MAX_SIZE];
            private int top = -1;

            void push(double value) {
                if (top >= data.length - 1) {
                    throw new IllegalArgumentException("操作数栈已满");
                }
                data[++top] = value;
            }

            double pop() {
                if (top == -1) {
                    throw new IllegalArgumentException("操作数不足");
                }
                return data[top--];
            }

            double peek() {
                if (top == -1) {
                    throw new IllegalArgumentException("表达式为空");
                }
                return data[top];
            }
        }

        private static void append(char[] postexp, int index, char value) {
            if (index >= postexp.length) {
                throw new IllegalArgumentException("表达式过长");
            }
            postexp[index] = value;
        }

        /** 将中缀表达式 exp 转换为以 # 分隔操作数的后缀表达式。 */
        static String trans(String exp) {
            if (exp == null) {
                throw new IllegalArgumentException("表达式不能为空");
            }

            SqStack optr = new SqStack();
            char[] postexp = new char[MAX_SIZE];
            int outputIndex = 0;
            int inputIndex = 0;

            while (inputIndex < exp.length()) {
                char ch = exp.charAt(inputIndex);

                if (Character.isWhitespace(ch)) {
                    inputIndex++;
                    continue;
                }

                if (ch >= '0' && ch <= '9') {
                    do {
                        append(postexp, outputIndex++, ch);
                        inputIndex++;
                        if (inputIndex >= exp.length()) {
                            break;
                        }
                        ch = exp.charAt(inputIndex);
                    } while (ch >= '0' && ch <= '9');
                    append(postexp, outputIndex++, '#');
                    continue;
                }

                switch (ch) {
                    case '(':
                        optr.push(ch);
                        inputIndex++;
                        break;

                    case ')': {
                        boolean foundLeftParen = false;
                        while (!optr.isEmpty()) {
                            char topOperator = optr.pop();
                            if (topOperator == '(') {
                                foundLeftParen = true;
                                break;
                            }
                            append(postexp, outputIndex++, topOperator);
                        }
                        if (!foundLeftParen) {
                            throw new IllegalArgumentException("括号不匹配");
                        }
                        inputIndex++;
                        break;
                    }

                    case '+':
                    case '-':
                        while (!optr.isEmpty() && optr.peek() != '(') {
                            append(postexp, outputIndex++, optr.pop());
                        }
                        optr.push(ch);
                        inputIndex++;
                        break;

                    case '*':
                    case '/':
                        while (!optr.isEmpty()
                                && (optr.peek() == '*' || optr.peek() == '/')) {
                            append(postexp, outputIndex++, optr.pop());
                        }
                        optr.push(ch);
                        inputIndex++;
                        break;

                    default:
                        throw new IllegalArgumentException("含有不支持的字符: " + ch);
                }
            }

            while (!optr.isEmpty()) {
                char topOperator = optr.pop();
                if (topOperator == '(') {
                    throw new IllegalArgumentException("括号不匹配");
                }
                append(postexp, outputIndex++, topOperator);
            }

            if (outputIndex == 0) {
                throw new IllegalArgumentException("表达式不能为空");
            }
            return new String(postexp, 0, outputIndex);
        }

        /** 计算后缀表达式的值。 */
        static double compvalue(String postexp) {
            SqStack1 opnd = new SqStack1();
            int index = 0;

            while (index < postexp.length()) {
                char ch = postexp.charAt(index);

                if (ch >= '0' && ch <= '9') {
                    double number = 0;
                    do {
                        number = 10 * number + (ch - '0');
                        index++;
                        if (index >= postexp.length()) {
                            break;
                        }
                        ch = postexp.charAt(index);
                    } while (ch >= '0' && ch <= '9');

                    if (index >= postexp.length() || postexp.charAt(index) != '#') {
                        throw new IllegalArgumentException("后缀表达式格式错误");
                    }
                    opnd.push(number);
                    index++;
                    continue;
                }

                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    double a = opnd.pop();
                    double b = opnd.pop();
                    double c;

                    switch (ch) {
                        case '+': c = b + a; break;
                        case '-': c = b - a; break;
                        case '*': c = b * a; break;
                        default:
                            if (a == 0) {
                                throw new ArithmeticException("除零错误");
                            }
                            c = b / a;
                            break;
                    }

                    opnd.push(c);
                    index++;
                    continue;
                }

                throw new IllegalArgumentException("后缀表达式格式错误");
            }

            double result = opnd.peek();
            if (opnd.top != 0) {
                throw new IllegalArgumentException("表达式格式错误");
            }
            return result;
        }

        private static String formatResult(double result) {
            if (Double.isFinite(result) && result == Math.rint(result)) {
                return String.format("%.0f", result);
            }
            return Double.toString(result);
        }

        public static void main(String[] args) {
            String exp;
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in, StandardCharsets.UTF_8));
                System.out.println("请输入算术表达式（支持正整数、+、-、*、/ 和圆括号）：");
                exp = reader.readLine();
            } catch (IOException e) {
                System.out.println("读取表达式失败。");
                return;
            }

            try {
                String postexp = trans(exp);
                double result = compvalue(postexp);
                System.out.println("中缀表达式:" + exp);
                System.out.println("后缀表达式:" + postexp);
                System.out.println("表达式的值:" + formatResult(result));
            } catch (ArithmeticException e) {
                System.out.println("除零错误！");
            } catch (IllegalArgumentException e) {
                System.out.println("表达式格式错误：" + e.getMessage());
            }
        }
    }
