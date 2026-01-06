package P700;
import java.util.*;


public class Combine 
{

    // ======================= PART A ===========================
    // ---------- Task 1: Stack Implementation ----------
    static class Stackimplementation 
    {
        private int maxSize;
        private int[] stackArray;
        private int top;

        public Stackimplementation(int size)
        {
            maxSize = size;
            stackArray = new int[maxSize];
            top = -1;
        }

        public void push(int value) 
        {
            if (top == maxSize - 1)
            {
                System.out.println("Stack is full!");
            } else
            {
                stackArray[++top] = value;
                System.out.println(value + " pushed into stack.");
            }
        }

        public int pop() 
        {
            if (isEmpty()) 
            {
                System.out.println("Stack is empty!");
                return -1;
            } else
            {
                int val = stackArray[top--];
                System.out.println(val + " popped from stack.");
                return val;
            }
        }

        public int peek()
        {
            if (isEmpty()) 
            {
                System.out.println("Stack is empty!");
                return -1;
            } else
            {
                return stackArray[top];
            }
        }

        public boolean isEmpty()
        {
            return (top == -1);
        }

        public void display()
        {
            if (isEmpty())
            {
                System.out.println("Stack is empty!");
            } else 
            {
                System.out.print("Stack elements: ");
                for (int i = top; i >= 0; i--)
                {
                    System.out.print(stackArray[i] + " ");
                }
                System.out.println();
            }
        }
    }

    // ---------- Task 2: Infix to Postfix Conversion ----------
    static class Infixtopostfix
    {
        static int priority(char ch)
        {
            if (ch == '+' || ch == '-') return 1;
            if (ch == '*' || ch == '/') return 2;
            if (ch == '^') return 3;
            return 0;
        }

        static String toPostfix(String infix)
        {
            Stack<Character> stack = new Stack<>();
            String postfix = "";

            for (int i = 0; i < infix.length(); i++) 
            {
                char c = infix.charAt(i);

                if (Character.isLetterOrDigit(c))
                {
                    postfix += c;
                } else if (c == '(')
                {
                    stack.push(c);
                } else if (c == ')') 
                {
                    while (!stack.isEmpty() && stack.peek() != '(')
                    {
                        postfix += stack.pop();
                    }
                    stack.pop();
                } else
                {
                    while (!stack.isEmpty() && priority(c) <= priority(stack.peek()))
                    {
                        postfix += stack.pop();
                    }
                    stack.push(c);
                }
            }
            while (!stack.isEmpty())
            {
                postfix += stack.pop();
            }
            return postfix;
        }
    }

    // ---------- Task 3: Infix to Prefix Conversion ----------
    static class Infixtoprefix
    {
        static int precedence(char ch)
        {
            switch (ch)
            {
                case '+':
                case '-':
                    return 1;
                case '*':
                case '/':
                    return 2;
                case '^':
                    return 3;
            }
            return -1;
        }

        static String infixToPostfix(String exp)
        {
            Stack<Character> stack = new Stack<>();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < exp.length(); i++)
            {
                char c = exp.charAt(i);
                if (c == ' ') continue;

                if (Character.isLetterOrDigit(c))
                {
                    result.append(c);
                } else if (c == '(')
                {
                    stack.push(c);
                } else if (c == ')')
                {
                    while (!stack.isEmpty() && stack.peek() != '(')
                    {
                        result.append(stack.pop());
                    }
                    stack.pop();
                } else 
                {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    {
                        result.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
            while (!stack.isEmpty()) result.append(stack.pop());
            return result.toString();
        }

        static String infixToPrefix(String infix) 
        {
            StringBuilder rev = new StringBuilder(infix).reverse();
            String reversed = rev.toString();

            char[] chars = reversed.toCharArray();
            for (int i = 0; i < chars.length; i++)
            {
                if (chars[i] == '(') chars[i] = ')';
                else if (chars[i] == ')') chars[i] = '(';
            }

            String postfix = infixToPostfix(new String(chars));
            return new StringBuilder(postfix).reverse().toString();
        }
    }

    // ======================= PART B ===========================
    // ---------- Task 4: Queue Implementation ----------
    static class Queue4 {
        private int front, rear, size;
        private int[] queue;

        public Queue4(int capacity)
        {
            queue = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void enqueue(int value)
        {
            if (size == queue.length) 
            {
                System.out.println("Queue is full!");
                return;
            }
            rear = (rear + 1) % queue.length;
            queue[rear] = value;
            size++;
            System.out.println(value + " added to queue.");
        }

        public int dequeue() 
        {
            if (isEmpty())
            {
                System.out.println("Queue is empty!");
                return -1;
            }
            int value = queue[front];
            front = (front + 1) % queue.length;
            size--;
            System.out.println(value + " removed from queue.");
            return value;
        }

        public int peek()
        {
            if (isEmpty())
            {
                System.out.println("Queue is empty!");
                return -1;
            }
            return queue[front];
        }

        public boolean isEmpty()
        {
            return size == 0;
        }

        public void display()
        {
            if (isEmpty())
            {
                System.out.println("Queue is empty!");
                return;
            }
            System.out.print("Queue elements: ");
            for (int i = 0; i < size; i++)
            {
                System.out.print(queue[(front + i) % queue.length] + " ");
            }
            System.out.println();
        }
    }

    // ---------- Task 5: Printer Queue Simulation ----------
    static class Printerqueue 
    {
        public static void simulate()
        {
            Queue<String> printerQueue = new LinkedList<>();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== PRINTER QUEUE MENU ===");
                System.out.println("1. Add Print Job");
                System.out.println("2. Process Next Job");
                System.out.println("3. Show Current Queue");
                System.out.println("4. Exit Printer Simulation");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch)
                {
                    case 1:
                        System.out.print("Enter document name: ");
                        String doc = sc.nextLine();
                        printerQueue.add(doc);
                        System.out.println("Enqueued: " + doc);
                        break;
                    case 2:
                        if (printerQueue.isEmpty())
                            System.out.println("No jobs to process!");
                        else 
                        {
                            String job = printerQueue.poll();
                            System.out.println("Processing: " + job);
                        }
                        break;
                    case 3:
                        System.out.println("Current Queue: " + printerQueue);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }
    }

    // ======================= MAIN MENU ===========================
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (true) 
        {
            System.out.println("\n=== STACK & QUEUE LAB MENU ===");
            System.out.println("1. Stack Implementation");
            System.out.println("2. Infix to Postfix Conversion");
            System.out.println("3. Infix to Prefix Conversion");
            System.out.println("4. Queue Implementation");
            System.out.println("5. Printer Queue Simulation");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) 
            {
                case 1:
                    System.out.print("Enter stack size: ");
                    int size = sc.nextInt();
                    Stackimplementation stack = new Stackimplementation(size);
                    while (true)
                    {
                        System.out.println("\n--- STACK MENU ---");
                        System.out.println("1. Push");
                        System.out.println("2. Pop");
                        System.out.println("3. Peek");
                        System.out.println("4. Display");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int s = sc.nextInt();
                        switch (s)
                        {
                            case 1:
                                System.out.print("Enter value: ");
                                stack.push(sc.nextInt());
                                break;
                            case 2:
                                stack.pop();
                                break;
                            case 3:
                                System.out.println("Top element: " + stack.peek());
                                break;
                            case 4:
                                stack.display();
                                break;
                            case 5:
                                s = -1;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        if (s == -1) break;
                    }
                    break;

                case 2:
                    System.out.print("Enter infix expression: ");
                    String infix1 = sc.nextLine();
                    System.out.println("Postfix: " + Infixtopostfix.toPostfix(infix1));
                    break;

                case 3:
                    System.out.print("Enter infix expression: ");
                    String infix2 = sc.nextLine();
                    System.out.println("Prefix: " + Infixtoprefix.infixToPrefix(infix2));
                    break;

                case 4:
                    System.out.print("Enter queue size: ");
                    int qsize = sc.nextInt();
                    Queue4 q = new Queue4(qsize);
                    while (true) 
                    {
                        System.out.println("\n--- QUEUE MENU ---");
                        System.out.println("1. Enqueue");
                        System.out.println("2. Dequeue");
                        System.out.println("3. Peek");
                        System.out.println("4. Display");
                        System.out.println("5. Back to Main Menu");
                        System.out.print("Enter choice: ");
                        int qch = sc.nextInt();
                        switch (qch)
                        {
                            case 1:
                                System.out.print("Enter value: ");
                                q.enqueue(sc.nextInt());
                                break;
                            case 2:
                                q.dequeue();
                                break;
                            case 3:
                                System.out.println("Front element: " + q.peek());
                                break;
                            case 4:
                                q.display();
                                break;
                            case 5:
                                qch = -1;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                        if (qch == -1) break;
                    }
                    break;

                case 5:
                    Printerqueue.simulate();
                    break;

                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

