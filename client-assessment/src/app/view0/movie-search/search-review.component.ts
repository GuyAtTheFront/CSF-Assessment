import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  movieSearchForm!: FormGroup;

  constructor( private fb: FormBuilder,
              private router: Router ) {};
  
  ngOnInit(): void {
    this.movieSearchForm = this.createForm();
  }

  createForm() {
    let grp = this.fb.group({
      movieName: this.fb.control<string>("", [Validators.pattern(`^\\s*[^\\s]{1}.*[^\\s]{1}?\\s*$`), Validators.minLength(2), Validators.required])
    })
    return grp;
  }

  processForm() {
    console.log(this.movieSearchForm.value);
    sessionStorage.setItem("query", this.movieSearchForm.get("movieName")?.value);
    this.router.navigate(["/list"], {queryParams: this.movieSearchForm.value})
  }

}
