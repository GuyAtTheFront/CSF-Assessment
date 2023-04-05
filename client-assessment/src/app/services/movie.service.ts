import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Review, UserComment } from '../models/models';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private URL_SEARCH = "/api/search";
  private URL_COMMENT = "/api/comment"

  constructor( private httpClient: HttpClient) { }

  searchMovie(search: string) {

    let params = new HttpParams()
                .append("query", search)

    return this.httpClient.get<{reviews: Review[]}>(this.URL_SEARCH, {params});
  }

  postComment(comment: UserComment) {

    const headers = new HttpHeaders()
                .append("Content-Type", "application/x-www-form-urlencoded");

    let payload = ["movieName="+comment.movieName, "posterName="+comment.posterName, 
                  "rating="+comment.rating, "comment="+comment.comment].join("&")

    return this.httpClient.post(this.URL_COMMENT, payload, {headers});

  }
}
