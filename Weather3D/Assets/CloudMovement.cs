using UnityEngine;
using System.Collections;

public class CloudMovement : MonoBehaviour {
    private Vector3 screenPos;
    private float posX;
    private float posY;
    private bool isKinect;
    private Vector3 righthandPos;
	private Vector3 lefthandPos;

    void Update()
    {
        
		/*righthandPos = GetComponent<FollowHand>().GetPosition(1);
		lefthandPos = GetComponent<FollowHand> ().GetPosition (0);

		if (GetComponent<FollowHand>().GetHandClosed(1)) {
			transform.position = righthandPos;
		}

		if (GetComponent<FollowHand>().GetHandClosed(0)) {
			transform.position = lefthandPos;
		}*/

        
    }

    void OnMouseDown()
    {
        
            screenPos = Camera.main.WorldToScreenPoint(transform.position);
            posX = Input.mousePosition.x - screenPos.x;
            posY = Input.mousePosition.y - screenPos.y;
       
    }

    void OnMouseDrag()
    {
        
            Vector3 curPos = new Vector3(Input.mousePosition.x - posX, Input.mousePosition.y - posY, screenPos.z);
            Vector3 worldPos = Camera.main.ScreenToWorldPoint(curPos);
            transform.position = worldPos;
       
    }
}
