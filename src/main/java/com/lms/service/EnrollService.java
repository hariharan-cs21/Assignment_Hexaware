package com.lms.service;

import com.lms.dao.CourseDao;
import com.lms.dao.EnrollDao;
import com.lms.dao.LearnerDao;
import com.lms.dao.impl.CourseDaoImpl;
import com.lms.dao.impl.EnrollDaoImpl;
import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.enums.Coupon;
import com.lms.exception.InvalidIdException;
import com.lms.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EnrollService {
    LearnerDao learnerDao=new LearnerDaoImpl();
    CourseDao courseDao=new CourseDaoImpl();
    EnrollDao enrollDao=new EnrollDaoImpl();
    public void enroll(int learnerId,int courseId) throws SQLException, InvalidIdException {
        Scanner sc=new Scanner(System.in);
        Enroll enroll=new Enroll();
        Learner learner=learnerDao.getById(learnerId);
        enroll.setLearner(learner);
        Course course=courseDao.getById(courseId);
        enroll.setCourse(course);
        System.out.println("Do you have coupon? (y/n)");
        String ans=sc.next();
        if(ans.equals("y")){
            System.out.println("Enter Coupon code: ");
            String code=sc.next().toUpperCase();
            Coupon coupon=Coupon.valueOf(code);
            double discount=(double)coupon.getDiscount();
            double discountedFee=course.getFee()-(course.getFee()*(discount/100));
            enroll.setFee_paid(discountedFee);
            enroll.setCoupone_used(coupon.toString());
            System.out.println("Coupon Applied! "+coupon);
            System.out.println("Final Price: "+discountedFee);
        }
        else enroll.setFee_paid(course.getFee());
        enroll.setDate_of_enroll(LocalDate.now());

        enrollDao.enroll(enroll);
    }
}
