import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { BehaviorSubject } from 'rxjs';
import jwt_decode from "jwt-decode"

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employeeStream: BehaviorSubject<any> = new BehaviorSubject({})
  employeeName: string | null = null;

  doLogin(credentials: any) {
    this.httpClient.post("http://localhost:8080/employee/login", credentials)
      .subscribe({
        next: (response: any) => {
          localStorage.setItem("token", response.jwt);
          const decoded: any = jwt_decode(response.jwt);
          this.employeeName = decoded.sub;
          this.employeeStream.next({
            action: "LOGIN_SUCCESS",
          })
        },
        error: (error => {
          this.employeeStream.next({
            action: "LOGIN_FAILED",
            error
          })
        })
      })
  }

  decodeToken() {
    let token = localStorage.getItem("token");
    if (token) {
      const decoded: any = jwt_decode(token);
      this.employeeName = decoded.sub;
    }
  }

  hire(formData: any) {
    formData.authorities = [
      'ROLE_EMPLOYEE'
    ]
    this.httpClient.post("http://localhost:8080/phone-shop/employees", formData)
      .subscribe({
        next: (response: any) => {
          this.employeeStream.next({
            action: "REGISTER_SUCCESS",
          })
        }
      })
    }

  // fire(employeeId: number) {
  //   this.httpClient.delete("http://localhost:8080/api/employees/" + employeeId).subscribe({
  //     next: e => {
  //       this.employeeStream = this.
  //     }
  //   })
  // }

  doLogout() {
    localStorage.removeItem('token')
    this.employeeStream.next({
      action: "LOGOUT_SUCCESS",
    })
  }

  isLoggedIn() {
    let token = localStorage.getItem("token")
    if (token)
      return true;
    else
      return false;
  }

  getToken() {
    let token = localStorage.getItem("token") || null
    return token;
  }

  constructor(private httpClient: HttpClient) { }
}
