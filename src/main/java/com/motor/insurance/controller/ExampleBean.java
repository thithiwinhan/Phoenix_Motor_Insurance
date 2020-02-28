package com.motor.insurance.controller;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
class ExampleBean{

        
      private boolean hidden=true;

   

       public boolean isHidden() {
		return hidden;
	}



	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}



	public void hideOrShow(){
           hidden=!hidden;

}
}