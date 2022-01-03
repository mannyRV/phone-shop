import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { faSleigh } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn:any = false;

  constructor(private userService: UserService) { 
  }

  handleLogout(){
    this.userService.doLogout();
  }

  ngOnInit(): void {
    this.isLoggedIn = this.userService.isLoggedIn()
    this.userService.userStream
      .subscribe((e: any) => {
        if (e.action === "LOGIN_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
        }
        if (e.action === "LOGOUT_SUCCESS") {
          this.isLoggedIn = this.userService.isLoggedIn()
        }
      })
  }
  

}
// mansoor
