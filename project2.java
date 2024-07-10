import java.util.*;

public class project2 {
public static class StudentRecord{
    int student_id;
    String name;
    String group_partner;
    int group_id;
    String assignment_name_id;
    String deadline;
    String status;
    float offline_evaluation_marks;
    float viva_marks;
    int height;

     StudentRecord left;
     StudentRecord right;

    StudentRecord( int student_id,String name,String group_partner,int group_id,String assignment_name_id,
    String deadline,String status,float offline_evaluation_marks,float viva_marks){
       this.student_id=student_id;
       this.name=name;
       this.group_partner=group_partner;
       this.group_id=group_id;
       this.assignment_name_id=assignment_name_id;
       this.deadline=deadline;
       this.status=status;
       this.offline_evaluation_marks=offline_evaluation_marks;
       this.viva_marks=viva_marks;
       this.height=1;
    }

}

public static class AssignmentRecord{
    int assignment_id;
    String topic_name;
    String status;
    int height;
    
    StudentAssignmentRecord student_group;
    AssignmentRecord left;
    AssignmentRecord right;

    AssignmentRecord(   int assignment_id,String topic_name){
        this.assignment_id=assignment_id;
        this.topic_name=topic_name;
        this.status="Declared";
        this.height=1;
    }

}

static StudentRecord student=null;
static AssignmentRecord assignment=null;


public static class StudentAssignmentRecord{
    int student_group_id;
    String status;
    float marks_given;
    int height;

    StudentAssignmentRecord left;
    StudentAssignmentRecord right;

    StudentAssignmentRecord(   int student_group_id,String status,float offline_marks1,float viva_marks1,float offline_marks2,float viva_marks2){
       this.student_group_id=student_group_id;
       this.status=status;
       this.marks_given=maxi(offline_marks1+viva_marks1 , offline_marks2+viva_marks2);
       this.height=1;
    }
}

public static float maxi(float a , float b)
{
	if(a>=b)
	{
		return a;
	}
	return b;
}

public static void visit_1(StudentRecord node){
   System.out.println(node.student_id);
   System.out.println(node.name);
   System.out.println(node.group_partner);
   System.out.println(node.group_id);
   System.out.println(node.assignment_name_id);
   System.out.println(node.deadline);
   System.out.println(node.status);
   System.out.println(node.offline_evaluation_marks);
   System.out.println(node.viva_marks);
   System.out.println("----------------------\n");
}

public static void inorder_1(StudentRecord node){
    if(node==null){
        return;
    }
    inorder_1(node.left);
    visit_1(node);
    inorder_1(node.right);
}

public static void visit_2(AssignmentRecord node){
    System.out.println(node.assignment_id);
    System.out.println(node.topic_name);
    System.out.println(node.status);
    System.out.println("----------------------\n");
}

public static void inorder_2(AssignmentRecord node){
    if(node==null){
        return;
    }
    inorder_2(node.left);
    visit_2(node);
    inorder_2(node.right);
}

public static void visit_3(StudentAssignmentRecord node){
    System.out.println(node.student_group_id);
    System.out.println(node.status);
    System.out.println(node.marks_given);
    System.out.println("----------------------\n");
}

public static void inorder_3(StudentAssignmentRecord node){
    if(node==null){
        return;
    }
    inorder_3(node.left);
    visit_3(node);
    inorder_3(node.right);
}

//For StudentRecord

public static int height_1(StudentRecord node){
    if(node==null) return 0;
    return node.height;
}
public static StudentRecord search_1(StudentRecord node, int key){
    if(node!=null){
     if(node.student_id==key){
        return node;
     }
    if(key<node.student_id){
        search_1(node.left, key);
    }
    else{
        search_1(node.right, key);
    }
}
return null;
}

public static StudentRecord insert_1(StudentRecord root,StudentRecord node){
    if(root==null){
        if(node==null){
            return null;
        }
        root=node;
    }
    else if(node.student_id<root.student_id){
        root.left=insert_1(root.left, node);
    }
    else{
        root.right=insert_1(root.right, node);
    }
    root.height=Math.max(height_1(root.left), height_1(root.right))+1;
    return rotate_1(root);
}

private static StudentRecord rotate_1(StudentRecord root){
    if((height_1(root.left)-height_1(root.right))>1){
        if((height_1(root.left.left)-height_1(root.left.right))>0){
            return rightRotate_1(root);
        }
        if((height_1(root.left.left)-height_1(root.left.right))<0){
            root.left=leftRotate_1(root.left);
            return rightRotate_1(root);
        }
    }

    if((height_1(root.left)-height_1(root.right))<-1){
        if((height_1(root.right.left)-height_1(root.right.right))<0){
            return leftRotate_1(root);
        }
        if((height_1(root.right.left)-height_1(root.right.right))>0){
            root.right=rightRotate_1(root.right);
            return leftRotate_1(root);
        }
    }
    return root;
}

private static StudentRecord rightRotate_1(StudentRecord p){
      StudentRecord c=p.left;
      StudentRecord temp=c.right;
      c.right=p;
      p.left=temp;
      p.height=Math.max(height_1(p.left), height_1(p.right))+1;
      c.height=Math.max(height_1(c.left), height_1(c.right))+1;
      return c;
}

private static StudentRecord leftRotate_1(StudentRecord p){
    StudentRecord c=p.right;
    StudentRecord temp=c.left;
    c.left=p;
    p.right=temp;
    p.height=Math.max(height_1(p.left), height_1(p.right))+1;
    c.height=Math.max(height_1(c.left), height_1(c.right))+1;
    return c;
}

//For AssignmentRecord

public static int height_2(AssignmentRecord node){
    if(node==null) return 0;
    return node.height;
}
public static AssignmentRecord search_2(AssignmentRecord node, int key){
    if(node!=null){
     if(node.assignment_id==key){
        return node;
     }
    if(key<node.assignment_id){
        search_2(node.left, key);
    }
    else{
        search_2(node.right, key);
    }}
    return node;
}

public static AssignmentRecord insert_2(AssignmentRecord root,AssignmentRecord node){
    if(root==null){
        if(node==null){
            return null;
        }
        root=node;
    }
    else if(node.assignment_id<root.assignment_id){
        root.left=insert_2(root.left, node);
    }
    else{
        root.right=insert_2(root.right, node);
    }
    root.height=Math.max(height_2(root.left), height_2(root.right))+1;
    return rotate_2(root);
}

private static AssignmentRecord rotate_2(AssignmentRecord root){
    if((height_2(root.left)-height_2(root.right))>1){
        if((height_2(root.left.left)-height_2(root.left.right))>0){
            return rightRotate_2(root);
        }
        if((height_2(root.left.left)-height_2(root.left.right))<0){
            root.left=leftRotate_2(root.left);
            return rightRotate_2(root);
        }
    }

    if((height_2(root.left)-height_2(root.right))<-1){
        if((height_2(root.right.left)-height_2(root.right.right))<0){
            return leftRotate_2(root);
        }
        if((height_2(root.right.left)-height_2(root.right.right))>0){
            root.right=rightRotate_2(root.right);
            return leftRotate_2(root);
        }
    }
    return root;
}

private static AssignmentRecord rightRotate_2(AssignmentRecord p){
      AssignmentRecord c=p.left;
      AssignmentRecord temp=c.right;
      c.right=p;
      p.left=temp;
      p.height=Math.max(height_2(p.left), height_2(p.right))+1;
      c.height=Math.max(height_2(c.left), height_2(c.right))+1;
      return c;
}

private static AssignmentRecord leftRotate_2(AssignmentRecord p){
    AssignmentRecord c=p.right;
    AssignmentRecord temp=c.left;
    c.left=p;
    p.right=temp;
    p.height=Math.max(height_2(p.left), height_2(p.right))+1;
    c.height=Math.max(height_2(c.left), height_2(c.right))+1;
    return c;
}

//For StudentAssignmentRecord

public static int height_3(StudentAssignmentRecord node){
    if(node==null) return 0;
    return node.height;
}

public static StudentAssignmentRecord search_3(StudentAssignmentRecord node, float key,int grp_id){
    if(node!=null){
     if((node.student_group_id==grp_id&&node.marks_given==key)){
        return node;
     }
    if(key<node.marks_given){
        search_3(node.left, key,grp_id);
    }
    else{
        search_3(node.right, key,grp_id);
    }}
    return null;
}

public static StudentAssignmentRecord search_3(StudentAssignmentRecord node, float key){
    if(node!=null){
     if(node.marks_given==key){
        return node;
     }
    if(key<node.marks_given){
        search_3(node.left, key);
    }
    else{
        search_3(node.right, key);
    }}
    return null;
}

public static StudentAssignmentRecord insert_3(StudentAssignmentRecord root,StudentAssignmentRecord node){
    if(root==null){
        if(node==null){
            return null;
        }
        root=node;
    }
    else if(node.marks_given<root.marks_given){
        root.left=insert_3(root.left, node);
    }
    else{
        root.right=insert_3(root.right, node);
    }
    root.height=Math.max(height_3(root.left), height_3(root.right))+1;
    return rotate_3(root);
}

private static StudentAssignmentRecord rotate_3(StudentAssignmentRecord root){
    if((height_3(root.left)-height_3(root.right))>1){
        if((height_3(root.left.left)-height_3(root.left.right))>0){
            return rightRotate_3(root);
        }
        if((height_3(root.left.left)-height_3(root.left.right))<0){
            root.left=leftRotate_3(root.left);
            return rightRotate_3(root);
        }
    }

    if((height_3(root.left)-height_3(root.right))<-1){
        if((height_3(root.right.left)-height_3(root.right.right))<0){
            return leftRotate_3(root);
        }
        if((height_3(root.right.left)-height_3(root.right.right))>0){
            root.right=rightRotate_3(root.right);
            return leftRotate_3(root);
        }
    }
    return root;
}

private static StudentAssignmentRecord rightRotate_3(StudentAssignmentRecord p){
      StudentAssignmentRecord c=p.left;
      StudentAssignmentRecord temp=c.right;
      c.right=p;
      p.left=temp;
      p.height=Math.max(height_3(p.left), height_3(p.right))+1;
      c.height=Math.max(height_3(c.left), height_3(c.right))+1;
      return c;
}

private static StudentAssignmentRecord leftRotate_3(StudentAssignmentRecord p){
    StudentAssignmentRecord c=p.right;
    StudentAssignmentRecord temp=c.left;
    c.left=p;
    p.right=temp;
    p.height=Math.max(height_3(p.left), height_3(p.right))+1;
    c.height=Math.max(height_3(c.left), height_3(c.right))+1;
    return c;
}

//Question - 1 -----------------------------------

public static StudentRecord insert_student(StudentRecord stu,int student_id1,int student_id2,String name,String group_partner,int group_id,String assignment_name_id,String deadline,String status, float offline_evaluation_marks1,float viva_marks1, float offline_evaluation_marks2, float viva_marks2, int assignment_id)
{
    StudentRecord node1=new StudentRecord(student_id1, name, group_partner, group_id, assignment_name_id, deadline, status, offline_evaluation_marks1, viva_marks1);
    StudentRecord node2= new StudentRecord(student_id2, group_partner, name, group_id, assignment_name_id, deadline, status, offline_evaluation_marks2, viva_marks2);
    if(search_1(stu, student_id1)!=null&&search_1(stu, student_id2)!=null){
        stu=insert_1(stu, node1);
        stu=insert_1(stu, node2);

        //Checking in Assignment Record -------------------------
        AssignmentRecord ptr=assignment;
        AssignmentRecord node=search_2(null, assignment_id);
        if(node==null){
            AssignmentRecord nptr= new AssignmentRecord(assignment_id, assignment_name_id);
            assignment=insert_2(assignment, nptr);
        }

        // Adding student groups ----------------------------
        StudentAssignmentRecord node3=new StudentAssignmentRecord(group_id, status, offline_evaluation_marks1, viva_marks1, offline_evaluation_marks2, viva_marks2);
        ptr=search_2(assignment, assignment_id);
        ptr.student_group=insert_3(ptr.student_group, node3);

    }  
    else{
        System.out.println("Student with same id already exists");
    }
    return student;
}

// Question 2 --------------------------------

public static AssignmentRecord insert_assignment(StudentRecord student,AssignmentRecord assignment,String topic_name, int assignment_id,int[] student_id,String[] name, int[] group_id,String deadline, String[] status, float[] offline_marks,float[] viva_marks,int num_stu)
{
    AssignmentRecord node1=new AssignmentRecord(assignment_id, topic_name);
    AssignmentRecord node2=assignment;
    AssignmentRecord found= search_2(node2, assignment_id);
    if(found!=null){
        System.out.println("An Assignment with same id already exists");
    }
    else{
			assignment = insert_2(assignment , node1);
		
            //Insert student grps--------------------------

            for (int i = 0; i < num_stu; i+=2) {
                StudentAssignmentRecord node3=new StudentAssignmentRecord(group_id[i], status[i], offline_marks[i], viva_marks[i], offline_marks[i+1], viva_marks[i+1]);
                node1.student_group=insert_3(node1.student_group, node3);
            }

            //Insert student records -----------------------

            for (int i = 0; i < num_stu; i+=2) {
                StudentRecord node4=new StudentRecord(student_id[i], name[i], name[i+1], group_id[i], topic_name, deadline, status[i], offline_marks[i], viva_marks[i]);
                StudentRecord node5=new StudentRecord(student_id[i+1], name[i+1], name[i], group_id[i], topic_name, deadline, status[i], offline_marks[i+1], viva_marks[i+1]);

                if(search_1(student, node4.student_id)!=null&&search_1(student, node5.student_id)!=null){
                    student=insert_1(student, node4);
                    student=insert_1(student, node5);
                }
                else{
                    System.out.println("Student with same id already exists");
                }
            }


    }
    return assignment;
}

// Question 3 -----------------------

public static int flag=1;

public static int check_status(StudentAssignmentRecord node){
    if(node!=null){
        check_status(node.left);
        if(!node.status.equals("Evaluated")){
            flag=0;
        }
        check_status(node.right);
    }

    return flag;
}

public static void check_asg_grp(AssignmentRecord node){
    int c;
    if(node!=null){
        check_asg_grp(node.left);
        flag=1;
        c=check_status(node.student_group);
        if(c==1){
            node.status="Evaluated";
        }
        check_asg_grp(node.right);
    }
}

public static void change_status(StudentRecord student,AssignmentRecord assignment,int student_id1 , int student_id2 , String status ,float offline_marks1 , float viva_marks1 , float offline_marks2 , float viva_marks2 , int assignment_id )
{
    float marks =0;
	//int grp_id ;
	StudentRecord ptr1 = search_1(student , student_id1);
	StudentRecord ptr2 = search_1(student , student_id2);
	if(ptr1!=null && ptr2!=null)
	{
		//grp_id = ptr1.group_id;
		marks  = maxi(ptr1.offline_evaluation_marks+ptr1.viva_marks , ptr2.offline_evaluation_marks+ptr2.viva_marks);
		
		ptr1.status=status;
		ptr1.offline_evaluation_marks = offline_marks1;
		ptr1.viva_marks = viva_marks1;
		
		ptr2.status=status;
		ptr2.offline_evaluation_marks = offline_marks2;
		ptr2.viva_marks = viva_marks2;
		
	}
	
	
	//// Changing in student group records 
	AssignmentRecord ptr = assignment;
	AssignmentRecord nptr = search_2(assignment , assignment_id);
	if(nptr!=null)
	{
		StudentAssignmentRecord ptr3 = search_3(nptr.student_group , marks);
			ptr3.status=status;
			ptr3.marks_given = maxi(offline_marks1+viva_marks1 , offline_marks2+viva_marks2);
		
	}
	
	/// Changing status of Assignment
	
	check_asg_grp(assignment);
}

// Question 4 ------------------------------

public static void print_declared_not_eval(AssignmentRecord node){
    AssignmentRecord ptr=node;
    if(ptr!=null){
        print_declared_not_eval(node.left);
        if(!ptr.status.equals("Evaluated")&&!ptr.status.equals("DueDateOver")){
            System.out.println(ptr.assignment_id);
            System.out.println(ptr.topic_name);
            System.out.println(ptr.status);
            System.out.println("---------------\n");
        }
        print_declared_not_eval(node.right);
    }
}

//Question 5 ----------------------------------

public static int found = 0;
public static void check_q5(StudentAssignmentRecord node)
{
	StudentAssignmentRecord ptr = node;
	if(ptr!=null)
	{
		check_q5(ptr.left);
		
		if(ptr.status.equals("NotSubmitted"))
		{
			System.out.println("Group id : "+ptr.student_group_id);
            System.out.println("Status : "+ptr.status);
			found = 1;
		}
		
		check_q5(ptr.right);
	}
}

public static void print_stud_not_submitted(AssignmentRecord assignment , StudentRecord student)
{
	AssignmentRecord ptr = assignment;
	if(ptr!=null)
	{
		print_stud_not_submitted(ptr.left , student);
		
		if(ptr.status.equals("DueDateOver"))
		{
			System.out.println("Assignment id : "+ptr.assignment_id);
            System.out.println("Topic Name : "+ptr.topic_name);
            System.out.println("Status : "+ptr.status);
			
			StudentAssignmentRecord ptr2 = ptr.student_group;
			if(ptr2!=null)
			{
				check_q5(ptr2);
				if(found == 0)
				{
					System.out.println("No One");
				}
				System.out.println("--------------------------\n");
			}
		}
		
		print_stud_not_submitted(ptr.right , student);
	}
}

// Question 6 ------------------------------

public static void print_stud_notEval_sub(StudentRecord node)
{
	StudentRecord ptr = node;
	if(ptr!=null)
	{
		print_stud_notEval_sub(ptr.left);
		
		if(ptr.status.equals("Submitted"))
		{
		        System.out.println("Group  id : "+ptr.group_id);
                System.out.println("Assignment Topic : "+ptr.assignment_name_id);
                System.out.println("Member 1 : "+ptr.name);
                System.out.println("Member 2 : "+ptr.group_partner);
                System.out.println("Submitted but not Evaluated");
		}
		else if(ptr.status.equals("Evaluated"))
		{
			if(ptr.viva_marks == 0)
			{
				System.out.println("Group id : "+ptr.group_id);
                System.out.println("Assignment Topic : "+ptr.assignment_name_id);
                System.out.println("Member 1 : "+ptr.name);
                System.out.println("Member 2 : "+ptr.group_partner);
                System.out.println("Evaluated but Vivva is Yet to be Completed");
			}
		}
		
		print_stud_notEval_sub(ptr.right);
	}
}

//Question 7 ------------------------------------

public static void print_dec_order(AssignmentRecord assignment , int assignment_id)
{
	AssignmentRecord ptr = search_2(assignment , assignment_id);
	if(ptr == null)
	{
		System.out.println("No such Assignment");
	}
	else
	{
		StudentAssignmentRecord ptr1 = ptr.student_group;
		System.out.println("Assignment id : "+ptr.assignment_id);
        System.out.println("Topic Name : "+ptr.topic_name);
		
		inorder_3(ptr1);
	}
}

// Question 8 ---------------------------------


public static StudentAssignmentRecord get_rightmost_node(StudentAssignmentRecord node)
{
	if(node!=null)
	{
		if(node.right == null)
		{
			return node;
		}
		if(node.right!=null)
		{
			return get_rightmost_node(node.right);
		}
	}
    return null;
}

public static void print_top_stud(AssignmentRecord node)
{
	AssignmentRecord ptr = node;
	if(ptr!=null)
	{
		print_top_stud(ptr.left);
		
		System.out.println("Assignment id : "+ptr.assignment_id);
        System.out.println("Topic Name : "+ptr.topic_name);
        StudentAssignmentRecord ptr1=ptr.student_group;
        StudentAssignmentRecord rigth_node=get_rightmost_node(ptr1);
        System.out.println("Group id : "+rigth_node.student_group_id);
        System.out.println("Top Marks Given : "+rigth_node.marks_given);
		
		print_top_stud(ptr.right);
	}

}

//Question 9 ------------------------------------


public static void range_search(AssignmentRecord node , int a1 , int a2)
{
	if(node!=null)
	{
		range_search(node.left , a1, a2);
		
		if(a1 < node.assignment_id && a2 > node.assignment_id)
		{
			System.out.println(node.assignment_id);
            System.out.println(node.topic_name);
            System.out.println(node.status);
            System.out.println(node.student_group);
            System.out.println("------------------\n");
		}
		
		range_search(node.right , a1, a2);
	}
}

public static void displayMarks(int student_id)
{
	StudentRecord ptr = search_1(student , student_id);
	if(ptr!=null)
	{
		System.out.println(ptr.student_id);
        System.out.println(ptr.name);
        System.out.println(ptr.status);
        System.out.println(ptr.offline_evaluation_marks);
        System.out.println(ptr.viva_marks);
        System.out.println("------------------------\n");
	}
	else
	{
		System.out.println("No such student found");
	}
}

public static void displayGroupDeatils(int student_id)
{
	StudentRecord ptr = search_1(student , student_id);
	if(ptr!=null)
	{
		System.out.println(ptr.student_id);
        System.out.println(ptr.name);
        System.out.println(ptr.group_partner);
        System.out.println(ptr.group_id);
        System.out.println(ptr.assignment_name_id);
        System.out.println(ptr.deadline);
        System.out.println(ptr.status);
        System.out.println(ptr.offline_evaluation_marks);
        System.out.println(ptr.viva_marks);
        System.out.println("------------------------\n");
	}
	else
	{
		System.out.println("No such student found");
	}
}


private static Scanner sc;
    
    public static void main(String[] args) {
      

        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the correct choice : ");
            System.out.println("1. Student");
            System.out.println("2. Teacher");
            System.out.println("3. Exit Program");
            int a = sc.nextInt();
            if (a == -1 || a == 3) {
                break;
            }
            switch (a) {
                case 1:
                    handleStudent();
                    break;
                case 2:
                    handleTeacher();
                    break;
                default:
                    System.out.println("Enter Valid Number\n\n");
                    break;
            }
        }
    }

    private static void handleStudent() {
        System.out.println("You chose 1\n\n");
        System.out.println("------------------Students Portal-------------------\n");
        
        System.out.print("Enter your name: ");
        String name = sc.next();
        System.out.print("Enter your password: ");
        int password = sc.nextInt();
        if (name == null || password == -1) {
            System.out.println("Error reading input.");
            return;
        }
        System.out.println(name+" "+password);
        
        StudentRecord ptr = search_1(student, password); 
        
        if (ptr != null) {
            System.out.println("Validated\n\n");
            while (true) {
                System.out.println("Choose the correct choice : ");
                System.out.println("1. Display your Marks");
                System.out.println("2. Display your Group Details");
                System.out.println("3. Exit this block");
                System.out.println("Enter your choice : ");
                int b = sc.nextInt();
                if (b == -1 || b == 3) {
                    System.out.println("Exiting this block");
                    break;
                }
                switch (b) {
                    case 1:
                        System.out.println("You chose 1");
                        displayMarks(password); 
                        break;
                    case 2:
                        System.out.println("You chose 2");
                        displayGroupDeatils(password); 
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            }
        }
        else{
            System.out.println("Invalid credentials");
        }
    }
    

    private static void handleTeacher() {
        System.out.println("You chose 2\n\n");
        System.out.println("------------------Teachers Portal-------------------\n");
        
        System.out.print("Enter your name: ");
        String name2 = sc.next();
        System.out.print("Enter your password: ");
        String password1 = sc.next();
        if (name2 == null || password1 == null) {
            System.out.println("Error reading input.");
            return;
        }

        if (name2.equals("Keskar") && password1.equals("keskar")) {
            System.out.println("Validated\n");
            while (true) {
                System.out.println("Choose the correct choice : ");
                System.out.println("1. Insert Student Record");
                System.out.println("2. Insert Assignment");
                System.out.println("3. Change Status");
                System.out.println("4. Print List of Assignments declared but not Evaluated");
                System.out.println("5. Print the list student groups who have not submitted the assignments even if the due-date is over. ");
                System.out.println("6. Print the list of student groups for whom the assignment is yet-to-be-evaluated even though they have been submitted.");
                System.out.println("7. Print the details of student-groups, in the decreasing order of marks for a particular assignment");
                System.out.println("8. Print student groups receiving top marks in each of the assignment given.");
                System.out.println("9. Range Search between 2 assignments");
                System.out.println("10. Exit this Block");
                System.out.println("Enter your choice : ");
                int b = sc.nextInt();
                if(b==10) break;
                switch (b) {
                    case 1:
                        handleInsertStudentRecord();
                        break;
                    case 2:
                        handleInsertAssignment();
                        break;
                    case 3:
                        handleChangeStatus();
                        break;
                    case 4:
                    System.out.println("Printing List of Assignments declared but not evaluated");
                        print_declared_not_eval(assignment); 
                        break;
                    case 5:
                    System.out.println("Printing the list student groups who have not submitted the assignments even if the due-date is over.");
                        print_stud_not_submitted(assignment, student);
                        break;
                    case 6:
                    System.out.println("Printing the list of student groups for whom the assignment is yet-to-be-evaluated even though they have been submitted.");
                        print_stud_notEval_sub(student); 
                        break;
                    case 7:
                    System.out.println("Printing the details of student-groups, in the Increasing order of marks for a particular assignment");
                        int assignment_id = sc.nextInt();
                        print_dec_order(assignment, assignment_id); 
                        break;
                    case 8:
                    System.out.println("Printing student groups receiving top marks in each of the assignment given.");
                        print_top_stud(assignment); 
                        break;
                    case 9:
                    System.out.println("Range Search");
                    System.out.println("Enter start of your Range : ");
                        int a1 = sc.nextInt();
                        System.out.println("Enter end of your Range : ");
                        int a2 = sc.nextInt();
                        range_search(assignment, a1, a2); 
                        break;   
                    default:
                        System.out.println("Enter valid Number\n");
                        break;
                }
            }
        } else {
            System.out.println("Incorrect Password");
            System.exit(0);
        }
    }
    private static void handleInsertStudentRecord() {
        System.out.println("Inserting student record-------->");
        System.out.print("Enter Student ID 1: ");
        int c = sc.nextInt();
        System.out.print("Enter Student ID 2: ");
        int d = sc.nextInt();
        System.out.print("Enter Name: ");
        String e = sc.next();
        System.out.print("Enter Group Partner: ");
        String f = sc.next();
        System.out.print("Enter Group ID: ");
        int o = sc.nextInt();
        System.out.print("Enter Assignment Name : ");
        String g = sc.next();
        System.out.print("Enter Assignment ID: ");
        int h = sc.nextInt();
        System.out.print("Enter Deadline: ");
        String i = sc.next();
        System.out.print("Enter Status: ");
        String j = sc.next();
        System.out.print("Enter Offline Evaluation Marks 1: ");
        float k = sc.nextFloat();
        System.out.print("Enter Viva Marks 1: ");
        float l = sc.nextFloat();
        System.out.print("Enter Offline Evaluation Marks 2: ");
        float m = sc.nextFloat();
        System.out.print("Enter Viva Marks 2: ");
        float n = sc.nextFloat();
    
        if (c == -1 || d == -1 || e == null || f == null || o == -1 || g == null || h == -1 || i == null || j == null || k == -1 || l == -1 || m == -1 || n == -1) {
            System.out.println("Error reading input.");
            return;
        }
        student = insert_student(student, c, d, e, f, o, g, i, j, k, l, m, n, h);
        inorder_1(student);
    }
    

    private static void handleInsertAssignment() {
        System.out.println("Inserting Assignment record---------->");
        System.out.println("Enter Assignment id , Topic name and deadline respectively : ");
        System.out.print("Enter Assignment id: ");
        int assignment_id = sc.nextInt();
        System.out.print("Enter Topic name: ");
        String topic_name = sc.next();
        System.out.print("Enter deadline: ");
        String deadline = sc.next();
        System.out.println("Enter number of students : ");
        int num = sc.nextInt();
        if (assignment_id == -1 || topic_name == null || deadline == null || num == -1) {
            System.out.println("Error reading input.");
            return;
        }
        int[] stud_id = new int[100];
        int[] grp_id = new int[100];
        String[] name = new String[100];
        String[] status = new String[100];
        float[] off_marks = new float[100];
        float[] viva_marks = new float[100];
        
        for (int p = 0; p < num; p++) {
            System.out.print("Enter Student ID: ");
            stud_id[p] = sc.nextInt();
            System.out.print("Enter Name: ");
            name[p] = sc.next();
            System.out.print("Enter Group ID: ");
            grp_id[p] = sc.nextInt();
            System.out.print("Enter Status: ");
            status[p] = sc.next();
            System.out.print("Enter Offline Evaluation Marks: ");
            off_marks[p] = sc.nextFloat();
            System.out.print("Enter Viva Marks: ");
            viva_marks[p] = sc.nextFloat();
            if (stud_id[p] == -1 || name[p] == null || grp_id[p] == -1 || status[p] == null || off_marks[p] == -1 || viva_marks[p] == -1) {
                System.out.println("Error reading input.");
                return;
            }
        }
        
        assignment = insert_assignment(student, assignment, topic_name, assignment_id, stud_id, name, grp_id, deadline, status, off_marks, viva_marks, num); 
        inorder_1(student); 
        inorder_2(assignment); 
    }

    private static void handleChangeStatus() {
        System.out.println("Changing status of a group ----------->");
        System.out.print("Enter Student ID 1: ");
        int s1 = sc.nextInt();
        System.out.print("Enter Student ID 2: ");
        int s2 = sc.nextInt();
        System.out.print("Enter Assignment ID: ");
        int assig_id = sc.nextInt();
        System.out.print("Enter Status (Evaluated/Not Evaluated): ");
        String stat = sc.next();
        if (s1 == -1 || s2 == -1 || assig_id == -1 || stat == null) {
            System.out.println("Error reading input.");
            return;
        }

        if (!stat.equals("Evaluated")) {
            change_status(student, assignment, s1, s2, stat, 0, 0, 0, 0, assig_id); 
        } else {
            System.out.print("Enter Offline Marks 1: ");
        float m1 = sc.nextFloat();
        System.out.print("Enter Viva Marks 1: ");
        float m2 = sc.nextFloat();
        System.out.print("Enter Offline Marks 2: ");
        float m3 = sc.nextFloat();
        System.out.print("Enter Viva Marks 2: ");
        float m4 = sc.nextFloat();
            if (m1 == -1 || m2 == -1 || m3 == -1 || m4 == -1) {
                System.out.println("Error reading input.");
                return;
            }
            change_status(student, assignment, s1, s2, stat, m1, m2, m3, m4, assig_id); 
        }
        inorder_1(student); 
        inorder_2(assignment); 
    }

    


}


    
