import { Request, Response, NextFunction } from 'express';
import { AuthService } from '@domain/services/AuthService';
import { MongoUserRepository } from '@infrastructure/database/repositories/MongoUserRepository';

const authService = new AuthService(new MongoUserRepository());

export const authMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  try {
    const authHeader = req.headers.authorization;
    if (!authHeader) {
      return res.status(401).json({ message: 'No token provided' });
    }

    const token = authHeader.split(' ')[1];
    const user = await authService.verifyToken(token);
    
    // Add user to request object
    (req as any).user = user;
    next();
  } catch (error) {
    return res.status(401).json({ message: 'Invalid token' });
  }
}; 