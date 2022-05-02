import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Client';

  ngOnInit(): void {
    console.log('Hello from the pim-client-angular AppComponent!');
    console.log('Lets see if the backend is working');
    fetch('http://localhost:4200/api/item/1',).then(res => console.log(res))
  }
}
