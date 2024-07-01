import { Component } from '@angular/core';

@Component({
  selector: 'app-house',
  templateUrl: './house.component.html',
  styleUrl: './house.component.css'
})
export class HouseComponent {
  showDetails = false;

  toggleDetails() {
    this.showDetails = !this.showDetails;
  }
}
