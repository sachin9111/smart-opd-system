import { Component, EventEmitter, Output } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

   userName = 'User';

   @Output()
  toggleSidebar = new EventEmitter<void>();

  onMenuClick() {
    this.toggleSidebar.emit();
  }

 

}
