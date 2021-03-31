import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { routes } from '../../consts';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.scss']
})
export class NotFoundComponent implements OnInit {
  public routes: typeof routes = routes;

  constructor(private router: Router) { }

  ngOnInit(): void {
    // this.router.navigate([this.routes.DASHBOARD]);
  }
}
