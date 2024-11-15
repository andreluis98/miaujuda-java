import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
    private baseUrl = 'https://miaujuda-backend.onrender.com';
    private baseUrlLocal = 'http://localhost:8080';

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
  
      return this.httpClient.post(`${this.baseUrl}/api/users/login`, body)
    }

    //Register Users
    getRegister(
      name: string,
      email: string,
      username: string,
      password: string
    ) {
      const body = {
        name: name,
        email: email,
        username: username,
        password: password
      }
  
      return this.httpClient.post(`${this.baseUrl}/api/users/register`, body)
    }

  //List the all pets
  getPetsList(): Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/pets`)
  }
  
  //Search by Enrollment
  getPetsByMat(mat: number){  
    return this.httpClient.get(`${this.baseUrl}/pets/mat/${mat}`)
  }

  //Create new pet
  createdPet(
    txPet: string,
    txSx: string,
    endereco: string,
    txStatus: string,
    txObs: string,
    petImage: string,
    userId: string,
  ) {
    const body = {
      txPet: txPet,
      txSx: txSx, 
      endereco: endereco,
      txStatus: txStatus,
      txObs: txObs,
      petImage: petImage,
      userId: userId,
    }
    return this.httpClient.post(`${this.baseUrl}/pets`, body)
  }

  //Update Pet
  updatePet(
    id: number,
    txPet: string,
    txSx: string,
    endereco: string,
    txStatus: string,
    txObs: string,
    petImage: string,
    userId: string,
  ) {
    const body = {
      id: id,
      txPet: txPet,
      txSx: txSx,
      endereco: endereco,
      txStatus: txStatus,
      txObs: txObs,
      petImage: petImage,
      userId: userId,
    }
    return this.httpClient.put(`${this.baseUrl}/pets`, body)
  }

    //Delete Pet
    deletePet(id: number): Observable<any> {
      return this.httpClient.delete(`${this.baseUrl}/pets/${id}`)
    }

}
