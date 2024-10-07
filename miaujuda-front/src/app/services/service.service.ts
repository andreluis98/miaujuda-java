import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
    private baseUrl = 'http://localhost:8080/pets';

  constructor(private httpClient: HttpClient) { }

    //Login
    getLogin(
      username: string,
      password: string
    ) {
      const body = {
        username: username,
        password: password
      }
  
      return this.httpClient.post(`${this.baseUrl}/login`, body)
    }

  //List the all pets
  getPetsList(): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}`)
  }
  
  //Search by Enrollment
  getPetsByMat(mat: number){  
    return this.httpClient.get(`${this.baseUrl}/mat/${mat}`)
  }

  //Create new pet
  createdPet(
    name: string,
    gender: string,
    status: string,
    address: string,
    observation: string,
    pet: string,
    usename: string,
    password: string,
  ) {
    const body = {
      name: name,
      gender: gender,
      status: status,
      address: address,
      observation: observation,
      pet: pet,
      usename: usename,
      password: password,
    }
    return this.httpClient.post(`${this.baseUrl}`, body)
  }

  //Update Pet
  updatePet(
    id: number,
    name?: string,
    gender?: string,
    status?: string,
    address?: string,
    observation?: string,
    pet?: string,
    usename?: string,
    password?: string,
  ) {
    const body = {
      id: id,
      name: name,
      gender: gender,
      status: status,
      address: address,
      observation: observation,
      pet: pet,
      usename: usename,
      password: password,
    }
    return this.httpClient.put(`${this.baseUrl}`, body)
  }

    //Delete Pet
    deletePet(id: number): Observable<any> {
      return this.httpClient.delete(`${this.baseUrl}/${id}`)
    }

}
