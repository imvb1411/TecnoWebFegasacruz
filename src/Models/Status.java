/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author imvargas
 */
public enum Status {
    Active{
        @Override
        public String toString() {
            return "1";
        }
    },
    Inactive{
        @Override
        public String toString() {
            return "0";
        }
    },
    All{
        @Override
        public String toString() {
            return "%%";
        }
    }
}
