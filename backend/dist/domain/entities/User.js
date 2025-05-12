"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.User = void 0;
class User {
    constructor(data) {
        this.id = data.id || '';
        this.email = data.email || '';
        this.password = data.password || '';
        this.fullName = data.fullName || '';
        this.role = data.role || 'user';
        this.createdAt = data.createdAt || new Date();
        this.updatedAt = data.updatedAt || new Date();
    }
    toJSON() {
        return {
            id: this.id,
            email: this.email,
            password: this.password,
            fullName: this.fullName,
            role: this.role,
            createdAt: this.createdAt,
            updatedAt: this.updatedAt
        };
    }
}
exports.User = User;
