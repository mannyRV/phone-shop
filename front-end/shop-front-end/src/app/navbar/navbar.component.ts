import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn:any =false;

  constructor(private userService: UserService) { 
    this.isLoggedIn = userService.isLoggedIn;
  }

  ngOnInit(): void {
  }

}
// mansoor
