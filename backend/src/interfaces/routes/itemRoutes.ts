import { Router, Request, Response, NextFunction } from 'express';
import { ItemController } from '../controllers/ItemController';
import { authenticateToken, requireAdmin } from '../middleware/auth';
import { ItemRepository } from '@domain/repositories/ItemRepository';

const router = Router();
const itemController = new ItemController();
const itemRepository = new ItemRepository();

// Public routes
router.get('/', itemController.getAllItems.bind(itemController));
router.get('/recent', itemController.getRecentItems.bind(itemController));
router.get('/today', itemController.getTodayItems.bind(itemController));
router.get('/:id', itemController.getItemById.bind(itemController));

// Protected routes
router.use(authenticateToken);
router.post('/', (req: Request, res: Response, next: NextFunction) => itemController.createItem(req as any, res, next));
router.get('/found', (req: Request, res: Response, next: NextFunction) => itemController.getItemsByFoundBy(req as any, res, next));
router.get('/claimed', (req: Request, res: Response, next: NextFunction) => itemController.getItemsByClaimedBy(req as any, res, next));
router.put('/:id', (req: Request, res: Response, next: NextFunction) => itemController.updateItem(req, res, next));
router.delete('/:id', (req: Request, res: Response, next: NextFunction) => itemController.deleteItem(req, res, next));
router.post('/:id/claim', (req: Request, res: Response, next: NextFunction) => itemController.claimItem(req as any, res, next));
router.post('/:id/return', (req: Request, res: Response, next: NextFunction) => itemController.markAsReturned(req, res, next));

// Admin routes
router.delete('/admin/items/:id', authenticateToken, requireAdmin, async (req, res) => {
  const itemId = req.params.id;
  try {
    const deleted = await itemRepository.delete(itemId);
    if (deleted) {
      res.json({ message: 'Item deleted successfully' });
    } else {
      res.status(404).json({ message: 'Item not found' });
    }
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete item' });
  }
});

router.get('/admin/items', authenticateToken, requireAdmin, async (req, res) => {
  try {
    const items = await itemRepository.findAll();
    res.json(items);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch items' });
  }
});

// Admin: update any item
router.put('/admin/items/:id', authenticateToken, requireAdmin, async (req, res) => {
  const itemId = req.params.id;
  try {
    const updatedItem = await itemRepository.update(itemId, req.body);
    if (updatedItem) {
      res.json({ message: 'Item updated', item: updatedItem });
    } else {
      res.status(404).json({ message: 'Item not found' });
    }
  } catch (error) {
    res.status(500).json({ message: 'Failed to update item' });
  }
});

export default router; 