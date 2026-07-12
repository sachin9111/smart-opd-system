import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { TokenService } from 'src/app/core/services/token.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  
    role: string | null = null;
    @Input()
    collapsed = false;

    constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
) {}

ngOnInit() {
    this.role = this.tokenService.getRoles();
  }

logout(): void {

    this.authService.logout().subscribe({

        next: () => {

            this.tokenService.clear();

            this.router.navigate(['/login']);

        },

        error: () => {

            this.tokenService.clear();

            this.router.navigate(['/login']);

        }

    });

}

}
