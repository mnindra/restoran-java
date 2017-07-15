/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import javax.swing.JFrame;

/**
 *
 * @author aka
 */
abstract public class BaseForm extends JFrame {
    
    protected String baseURL = "http://localhost:8000/api/";
    
    protected abstract void clearForm();
}
